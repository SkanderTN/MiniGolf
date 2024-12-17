package main.ui;

import main.geometry.Point;
import main.game.Balle;
import main.game.Trou;
import main.game.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.List;

public class AfficheurCanvas {
    private final Canvas canvas;
    private final GraphicsContext contexteGraphique;
    private final Balle balle;
    private final Trou trou;
    private final Image background;

    private List<Obstacle> obstacles;

    public AfficheurCanvas(double largeur, double hauteur, Balle balle, Trou trou, List<Obstacle> obstacles) {
        this.canvas = new Canvas(largeur, hauteur);
        this.contexteGraphique = canvas.getGraphicsContext2D();
        this.balle = balle;
        this.trou = trou;

        this.background = new Image(getClass().getResource("/main/ressources/grass.png").toExternalForm());
        this.obstacles = obstacles; 

        dessiner();
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void dessiner() {
        dessinerBackground();
        dessinerTrou();
        dessinerFlag();
        dessinerObstacles(); 
        dessinerBalle();
    }

    private void dessinerBackground() {
        /*try {
            contexteGraphique.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

             // Overlay a semi-transparent rectangle to adjust visibility
            contexteGraphique.setFill(new Color(1, 1, 1, 0.1)); // White with 50% opacity
            contexteGraphique.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        } catch (Exception e) {*/
            LinearGradient gradient = new LinearGradient(0, 0, 0, canvas.getHeight(),
                false, null, 
                new Stop(0, Color.CYAN), 
                new Stop(1, Color.GREEN)  
            );
            contexteGraphique.setFill(gradient);
            contexteGraphique.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //}
    }

    public void dessinerFlag() {
        double flagPoleX = trou.getPosition().getX();
        double flagPoleY = trou.getPosition().getY() - trou.getRayon();
        double flagHeight = 50; // Height of the flagpole
        double flagWidth = 20;  // Width of the flag

        contexteGraphique.setStroke(Color.BLACK);
        contexteGraphique.setLineWidth(2);
        contexteGraphique.strokeLine(flagPoleX, flagPoleY, flagPoleX, flagPoleY - flagHeight);

        double[] xPoints = {
            flagPoleX,
            flagPoleX + flagWidth, 
            flagPoleX 
        };
        double[] yPoints = {
            flagPoleY - flagHeight,
            flagPoleY - flagHeight + flagWidth / 2, 
            flagPoleY - flagHeight + flagWidth 
        };

        contexteGraphique.setFill(Color.RED); 
        contexteGraphique.fillPolygon(xPoints, yPoints, 3); 
    }

    public void dessinerBalle() {
        double centerX = balle.getPosition().getX();
        double centerY = balle.getPosition().getY();
        double rayon = balle.getRayon();

        RadialGradient gradient = new RadialGradient(
            0, 0, centerX, centerY, rayon, false, null,
            new Stop(0, Color.WHITE),
            new Stop(1, Color.LIGHTGRAY)
        );

        contexteGraphique.setFill(gradient);
        contexteGraphique.fillOval(centerX - rayon, centerY - rayon, rayon * 2, rayon * 2);

        contexteGraphique.setFill(Color.DARKGRAY);
        double dimpleRadius = rayon * 0.1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                double offsetX = (i - 2) * dimpleRadius * 2.5;
                double offsetY = (j - 2) * dimpleRadius * 2.5;
                if (offsetX * offsetX + offsetY * offsetY < rayon * rayon) {
                    contexteGraphique.fillOval(
                        centerX + offsetX - dimpleRadius,
                        centerY + offsetY - dimpleRadius,
                        dimpleRadius * 2,
                        dimpleRadius * 2
                    );
                }
            }
        }
    }

    public void dessinerTrou() {
        contexteGraphique.setFill(Color.BLACK);
        contexteGraphique.fillOval(
            trou.getPosition().getX() - trou.getRayon(),
            trou.getPosition().getY() - trou.getRayon(),
            trou.getRayon() * 2,
            trou.getRayon() * 2
        );
    }

    public void dessinerObstacles() {
        for (Obstacle obstacle : obstacles) {
            contexteGraphique.setFill(Color.BROWN); 
            if (obstacle.getType().equals("Sable")) {
                contexteGraphique.setFill(Color.SANDYBROWN); 
            }

            contexteGraphique.fillRect(
                obstacle.getPosition().getX(),
                obstacle.getPosition().getY(),
                obstacle.getLargeur(),
                obstacle.getHauteur()
            );
        }
    }

    public void dessinerTrajectoire(Point debut, Point fin) {
        dessiner();
        contexteGraphique.setStroke(Color.GRAY);
        contexteGraphique.strokeLine(debut.getX(), debut.getY(), fin.getX(), fin.getY());
    }
}
