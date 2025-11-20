package ITEC3150.ConnectFourEC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ConnectFourServer extends Application implements ConnectFourConstants {
    private int sessionNo = 1; // Number a session

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        TextArea taLog = new TextArea();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(taLog), 450, 200);
        primaryStage.setTitle("ConnectFourServer"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8001);
                Platform.runLater(() -> taLog.appendText(new Date() +
                        ": Server started at socket 8001\n"));

                // Ready to create a session for every two players
                while (true) {
                    Platform.runLater(() -> taLog.appendText(new Date() +
                            ": Wait for players to join session " + sessionNo + '\n'));

                    // Connect to player 1
                    Socket player1 = serverSocket.accept();

                    Platform.runLater(() -> {
                        taLog.appendText(new Date() + ": Player 1 joined session "
                                + sessionNo + '\n');
                        taLog.appendText("Player 1's IP address: " +
                                player1.getInetAddress().getHostAddress() + '\n');
                    });

                    // Notify that the player is Player 1
                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    // Connect to player 2
                    Socket player2 = serverSocket.accept();

                    Platform.runLater(() -> {
                        taLog.appendText(new Date() +
                                ": Player 2 joined session " + sessionNo + '\n');
                        taLog.appendText("Player 2's IP address: " +
                                player2.getInetAddress().getHostAddress() + '\n');
                    });

                    // Notify that the player is Player 2
                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    // Display this session and increment session number
                    Platform.runLater(() -> taLog.appendText(new Date() +
                            ": Start a thread for session " + sessionNo++ + '\n'));

                    // Launch a new thread for this session of two players
                    new Thread(new HandleASession(player1, player2)).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    // Define the thread class for handling a new session for two players
    class HandleASession implements Runnable, ConnectFourConstants {
        private Socket player1;
        private Socket player2;

        // Create and initialize cells (6 rows x 7 columns)
        private char[][] cell = new char[6][7];

        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataInputStream fromPlayer2;
        private DataOutputStream toPlayer2;

        /** Construct a thread */
        public HandleASession(Socket player1, Socket player2) {
            this.player1 = player1;
            this.player2 = player2;

            // Initialize cells
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 7; j++)
                    cell[i][j] = ' ';
        }

        /** Implement the run() method for the thread */
        public void run() {
            try {
                // Create data input and output streams
                fromPlayer1 = new DataInputStream(player1.getInputStream());
                toPlayer1 = new DataOutputStream(player1.getOutputStream());
                fromPlayer2 = new DataInputStream(player2.getInputStream());
                toPlayer2 = new DataOutputStream(player2.getOutputStream());

                // Write anything to notify player 1 to start
                toPlayer1.writeInt(1);

                // Continuously serve the players and determine and report
                // the game status to the players
                while (true) {
                    // Receive a move from player 1 (column only)
                    int column = fromPlayer1.readInt();
                    int row = dropPiece(column, 'R'); // Red for player 1

                    // Check if Player 1 wins
                    if (isWon('R')) {
                        toPlayer1.writeInt(PLAYER1_WON);
                        toPlayer2.writeInt(PLAYER1_WON);
                        sendMove(toPlayer2, row, column);
                        break; // Break the loop
                    } else if (isFull()) { // Check if all cells are filled
                        toPlayer1.writeInt(DRAW);
                        toPlayer2.writeInt(DRAW);
                        sendMove(toPlayer2, row, column);
                        break;
                    } else {
                        // Notify player 2 to take the turn
                        toPlayer2.writeInt(CONTINUE);

                        // Send player 1's selected row and column to player 2
                        sendMove(toPlayer2, row, column);
                    }

                    // Receive a move from Player 2 (column only)
                    column = fromPlayer2.readInt();
                    row = dropPiece(column, 'Y'); // Yellow for player 2

                    // Check if Player 2 wins
                    if (isWon('Y')) {
                        toPlayer1.writeInt(PLAYER2_WON);
                        toPlayer2.writeInt(PLAYER2_WON);
                        sendMove(toPlayer1, row, column);
                        break;
                    } else {
                        // Notify player 1 to take the turn
                        toPlayer1.writeInt(CONTINUE);

                        // Send player 2's selected row and column to player 1
                        sendMove(toPlayer1, row, column);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /** Drop a piece in the specified column and return the row it landed in */
        private int dropPiece(int column, char token) {
            // Start from bottom row and move up to find first empty cell
            for (int row = 5; row >= 0; row--) {
                if (cell[row][column] == ' ') {
                    cell[row][column] = token;
                    return row;
                }
            }
            return -1; // Column is full (shouldn't happen with proper validation)
        }

        /** Send the move to other player */
        private void sendMove(DataOutputStream out, int row, int column) throws IOException {
            out.writeInt(row); // Send row index
            out.writeInt(column); // Send column index
        }

        /** Determine if the board is full */
        private boolean isFull() {
            // Check only top row
            for (int j = 0; j < 7; j++)
                if (cell[0][j] == ' ')
                    return false;
            return true;
        }

        /** Determine if the player with the specified token wins */
        private boolean isWon(char token) {
            // Check horizontal
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 4; col++) {
                    if (cell[row][col] == token &&
                            cell[row][col + 1] == token &&
                            cell[row][col + 2] == token &&
                            cell[row][col + 3] == token) {
                        return true;
                    }
                }
            }

            // Check vertical
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 7; col++) {
                    if (cell[row][col] == token &&
                            cell[row + 1][col] == token &&
                            cell[row + 2][col] == token &&
                            cell[row + 3][col] == token) {
                        return true;
                    }
                }
            }

            // Check diagonal (down-right)
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 4; col++) {
                    if (cell[row][col] == token &&
                            cell[row + 1][col + 1] == token &&
                            cell[row + 2][col + 2] == token &&
                            cell[row + 3][col + 3] == token) {
                        return true;
                    }
                }
            }

            // Check diagonal (down-left)
            for (int row = 0; row < 3; row++) {
                for (int col = 3; col < 7; col++) {
                    if (cell[row][col] == token &&
                            cell[row + 1][col - 1] == token &&
                            cell[row + 2][col - 2] == token &&
                            cell[row + 3][col - 3] == token) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
