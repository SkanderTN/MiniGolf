package main.game;

import main.geometry.Point;

public class Mur extends Obstacle {
    private final String orientation; // "vertical" ou "horizontal"

    public Mur(Point position, double largeur, double hauteur, String orientation) {
        super(position, largeur, hauteur, "Mur");
        this.orientation = orientation;
    }

    @Override
    public void appliquerEffet(Balle balle) {
        if (orientation == "vertical") {
            balle.calculerTrajectoire(180 - balle.getAngle(), balle.getVitesse() * 0.8);
        } else if (orientation == "horizontal") {
            balle.calculerTrajectoire(-balle.getAngle(), balle.getVitesse() * 0.8);
        }
    }
}
