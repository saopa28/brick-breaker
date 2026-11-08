package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Score {

    /** Adds a label to the current root and animates it, all on the FX thread. */
    private void animate(final Label label, final Main main, final double fromScale, final double midScale, final double toScale) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final Pane root = main.root;
                if (root == null) {
                    return;
                }
                root.getChildren().add(label);
                label.setScaleX(fromScale);
                label.setScaleY(fromScale);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(label.scaleXProperty(), fromScale),
                                new KeyValue(label.scaleYProperty(), fromScale),
                                new KeyValue(label.opacityProperty(), 1.0)),
                        new KeyFrame(Duration.millis(150),
                                new KeyValue(label.scaleXProperty(), midScale),
                                new KeyValue(label.scaleYProperty(), midScale)),
                        new KeyFrame(Duration.millis(300),
                                new KeyValue(label.scaleXProperty(), toScale),
                                new KeyValue(label.scaleYProperty(), toScale),
                                new KeyValue(label.opacityProperty(), 0.0))
                );
                timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.getChildren().remove(label);
                    }
                });
                timeline.play();
            }
        });
    }

    public void show(final double x, final double y, int score, final Main main) {
        String sign = score >= 0 ? "+" : "";
        final Label label = new Label(sign + score);
        label.setTranslateX(x);
        label.setTranslateY(y);
        animate(label, main, 0, 10, 20);
    }

    public void showMessage(String message, final Main main) {
        final Label label = new Label(message);
        label.setTranslateX(220);
        label.setTranslateY(340);
        animate(label, main, 10, 0, 10);
    }

    public void showGameOver(final Main main) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = new Label("Game Over :(");
                label.setTranslateX(200);
                label.setTranslateY(250);
                label.setScaleX(2);
                label.setScaleY(2);

                Button restart = new Button("Restart");
                restart.setTranslateX(220);
                restart.setTranslateY(300);
                restart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        main.restartGame();
                    }
                });

                main.root.getChildren().addAll(label, restart);

            }
        });
    }

    public void showWin(final Main main) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = new Label("You Win :)");
                label.setTranslateX(200);
                label.setTranslateY(250);
                label.setScaleX(2);
                label.setScaleY(2);

                Button restart = new Button("Play Again");
                restart.setTranslateX(220);
                restart.setTranslateY(300);
                restart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        main.restartGame();
                    }
                });

                main.root.getChildren().addAll(label, restart);

            }
        });
    }
}
