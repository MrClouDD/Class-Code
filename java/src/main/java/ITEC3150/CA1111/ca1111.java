package ITEC3150.CA1111;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ca1111 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        int rows = 4;
        int cols = 5;
        int radius = 20;

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Circle circle = new Circle();
                circle.centerXProperty().bind(
                        pane.widthProperty().multiply(i + 1).divide(cols + 1));

                circle.centerYProperty().bind(
                        pane.heightProperty().multiply(j + 1).divide(rows + 1));

                circle.radiusProperty().bind(
                        pane.widthProperty().add(pane.heightProperty())
                                .divide(2 * Math.max(rows, cols) * 2));

                circle.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.BLACK);
                pane.getChildren().add(circle);
            }
        }

        primaryStage.setTitle("Multiple Circles");
        primaryStage.setScene(new Scene(pane, 300, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
