package main.ui;

import main.geometry.Point;
import main.game.Balle;

public class GestionSouris {
    private Point debutGlisse;

    public void gererAppuiSouris(double x, double y) {
        debutGlisse = new Point(x, y);
    }

    public Point getDebutGlisse() {
        return debutGlisse;
    }

    public void gererRelacheSouris(double x, double y, Balle balle) {
        double dx = x - debutGlisse.getX();
        double dy = y - debutGlisse.getY();
        double force = Math.sqrt(dx * dx + dy * dy) / 10; 
        double angle = Math.toDegrees(Math.atan2(dy, dx));
        balle.calculerTrajectoire(angle, force);
    }
}
