package ITEC3150.ConnectFourEC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ConnectFourClient extends Application implements ConnectFourConstants {
    // Indicate whether the player has the turn
    private boolean myTurn = false;

    // Indicate the token for the player
    private char myToken = ' ';

    // Indicate the token for the other player
    private char otherToken = ' ';

    // Create and initialize cells (6 rows x 7 columns)
    private Cell[][] cell = new Cell[6][7];

    // Create and initialize a title label
    private Label lblTitle = new Label();

    // Create and initialize a status label
    private Label lblStatus = new Label();

    // Indicate selected column by the current move
    private int columnSelected;

    // Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // Continue to play?
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;

    // Host name or ip
    private String host = "localhost";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Pane to hold cells
        GridPane pane = new GridPane();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                pane.add(cell[i][j] = new Cell(i, j), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblTitle);
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 560, 520);
        primaryStage.setTitle("ConnectFourClient"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Connect to the server
        connectToServer();
    }

    private void connectToServer() {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(host, 8001);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Control the game on a separate thread
        new Thread(() -> {
            try {
                // Get notification from the server
                int player = fromServer.readInt();

                // Am I player 1 or 2?
                if (player == PLAYER1) {
                    myToken = 'R'; // Red
                    otherToken = 'Y'; // Yellow
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 1 with RED pieces");
                        lblStatus.setText("Waiting for player 2 to join");
                    });

                    // Receive startup notification from the server
                    fromServer.readInt(); // Whatever read is ignored

                    // The other player has joined
                    Platform.runLater(() -> lblStatus.setText("Player 2 has joined. I start first"));

                    // It is my turn
                    myTurn = true;
                } else if (player == PLAYER2) {
                    myToken = 'Y'; // Yellow
                    otherToken = 'R'; // Red
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 2 with YELLOW pieces");
                        lblStatus.setText("Waiting for player 1 to move");
                    });
                }

                // Continue to play
                while (continueToPlay) {
                    if (player == PLAYER1) {
                        waitForPlayerAction(); // Wait for player 1 to move
                        sendMove(); // Send the move to the server
                        receiveInfoFromServer(); // Receive info from the server
                    } else if (player == PLAYER2) {
                        receiveInfoFromServer(); // Receive info from the server
                        waitForPlayerAction(); // Wait for player 2 to move
                        sendMove(); // Send player 2's move to the server
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /** Wait for the player to mark a cell */
    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }

        waiting = true;
    }

    /** Send this player's move to the server */
    private void sendMove() throws IOException {
        toServer.writeInt(columnSelected); // Send the selected column
    }

    /** Receive info from the server */
    private void receiveInfoFromServer() throws IOException {
        // Receive game status
        int status = fromServer.readInt();

        if (status == PLAYER1_WON) {
            // Player 1 won, stop playing
            continueToPlay = false;
            if (myToken == 'R') {
                Platform.runLater(() -> lblStatus.setText("I won! (RED)"));
            } else if (myToken == 'Y') {
                Platform.runLater(() -> lblStatus.setText("Player 1 (RED) has won!"));
                receiveMove();
            }
        } else if (status == PLAYER2_WON) {
            // Player 2 won, stop playing
            continueToPlay = false;
            if (myToken == 'Y') {
                Platform.runLater(() -> lblStatus.setText("I won! (YELLOW)"));
            } else if (myToken == 'R') {
                Platform.runLater(() -> lblStatus.setText("Player 2 (YELLOW) has won!"));
                receiveMove();
            }
        } else if (status == DRAW) {
            // No winner, game is over
            continueToPlay = false;
            Platform.runLater(() -> lblStatus.setText("Game is over, no winner!"));

            if (myToken == 'Y') {
                receiveMove();
            }
        } else {
            receiveMove();
            Platform.runLater(() -> lblStatus.setText("My turn"));
            myTurn = true; // It is my turn
        }
    }

    private void receiveMove() throws IOException {
        // Get the other player's move
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        Platform.runLater(() -> cell[row][column].setToken(otherToken));
    }

    // An inner class for a cell
    public class Cell extends Pane {
        // Indicate the row and column of this cell in the board
        private int row;
        private int column;

        // Token used for this cell
        private char token = ' ';

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(80, 80);
            setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        /** Return token */
        public char getToken() {
            return token;
        }

        /** Set a new token */
        public void setToken(char c) {
            token = c;
            repaint();
        }

        protected void repaint() {
            if (token == 'R') {
                // Red circle for player 1
                Circle circle = new Circle(this.getWidth() / 2,
                        this.getHeight() / 2, this.getWidth() / 2 - 10);
                circle.centerXProperty().bind(this.widthProperty().divide(2));
                circle.centerYProperty().bind(this.heightProperty().divide(2));
                circle.radiusProperty().bind(
                        this.widthProperty().divide(2).subtract(10));
                circle.setFill(Color.RED);
                circle.setStroke(Color.DARKRED);
                circle.setStrokeWidth(2);

                getChildren().add(circle);
            } else if (token == 'Y') {
                // Yellow circle for player 2
                Circle circle = new Circle(this.getWidth() / 2,
                        this.getHeight() / 2, this.getWidth() / 2 - 10);
                circle.centerXProperty().bind(this.widthProperty().divide(2));
                circle.centerYProperty().bind(this.heightProperty().divide(2));
                circle.radiusProperty().bind(
                        this.widthProperty().divide(2).subtract(10));
                circle.setFill(Color.YELLOW);
                circle.setStroke(Color.GOLD);
                circle.setStrokeWidth(2);

                getChildren().add(circle);
            }
        }

        /* Handle a mouse click event */
        private void handleMouseClick() {
            // If the player has the turn, drop piece in this column
            if (myTurn) {
                // Find the lowest available row in this column
                int targetRow = findLowestEmptyRow(column);

                if (targetRow != -1) {
                    // Valid move
                    cell[targetRow][column].setToken(myToken);
                    myTurn = false;
                    columnSelected = column;
                    lblStatus.setText("Waiting for the other player to move");
                    waiting = false; // Just completed a successful move
                }
            }
        }

        /** Find the lowest empty row in the specified column */
        private int findLowestEmptyRow(int col) {
            for (int r = 5; r >= 0; r--) {
                if (cell[r][col].getToken() == ' ') {
                    return r;
                }
            }
            return -1; // Column is full
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
