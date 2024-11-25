package main;

import main.geometry.Point;
import main.game.Balle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;  
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


public class Main extends Application {

    private Point startDrag;  // The starting point of the mouse drag
    private Balle balle;      // The ball object

    @Override
    public void start(Stage stage) {
        // Create a canvas to draw on
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initial position for the ball
        Point positionInitiale = new Point(100, 100);
        balle = new Balle(positionInitiale, Color.BLUE, 10.0, 0.98);  // Corrected radius as 10.0 (double)

        // Draw the initial state of the ball
        drawBall(gc);

        // Mouse event handlers
        canvas.setOnMousePressed((MouseEvent e) -> {
            // Record the starting position of the drag
            startDrag = new Point(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged((MouseEvent e) -> {
            // Draw the ball and the trajectory line as the mouse is dragged
            double endX = e.getX();
            double endY = e.getY();

            drawBall(gc);  // Redraw the ball at its current position

            // Draw a line from the start drag point to the current mouse position
            gc.setStroke(Color.GRAY);
            gc.strokeLine(startDrag.getX(), startDrag.getY(), endX, endY);
        });

        canvas.setOnMouseReleased((MouseEvent e) -> {
            // On mouse release, calculate the direction and force
            double endX = e.getX();
            double endY = e.getY();

            // Calculate force and angle based on the distance and direction of the drag
            double dx = endX - startDrag.getX();
            double dy = endY - startDrag.getY();
            double force = Math.sqrt(dx * dx + dy * dy) / 10;  // Scale down force for better control
            double angle = Math.toDegrees(Math.atan2(dy, dx));

            // Set the ball's trajectory and start the movement
            balle.calculerTrajectoire(angle, force);

            // Animate the ball's movement
            new Thread(() -> {
                while (balle.getVitesse() > 0) {
                    balle.deplacer();
                    drawBall(gc);  // Redraw the ball at each step

                    try {
                        Thread.sleep(50); // Update every 50 milliseconds
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        });

        // Setup the scene and the stage
        Pane root = new Pane(canvas);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Mini Golf - Ball Movement");
        stage.show();
    }

    private void drawBall(GraphicsContext gc) {
        // Clear the canvas
        gc.clearRect(0, 0, 800, 600);

        // Draw the ball at its current position
        gc.setFill(balle.getCouleur());
        gc.fillOval(balle.getPosition().getX() - balle.getRayon(), balle.getPosition().getY() - balle.getRayon(),
                    balle.getRayon() * 2, balle.getRayon() * 2);
    }

    public static void main(String[] args) {
        launch();
    }
}
