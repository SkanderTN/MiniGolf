package main.game;

import main.geometry.Point;

public abstract class Obstacle {
    protected Point position;
    protected double largeur;
    protected double hauteur;
    protected String type; // enumeration

    public Obstacle(Point position, double largeur, double hauteur, String type) {
        this.position = position;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.type = type;
    }

    public abstract void appliquerEffet(Balle balle); 
    
    public boolean verifierCollision(Balle balle) {
        Point positionBalle = balle.getPosition();
        double rayonBalle = balle.getRayon();

        return positionBalle.getX() + rayonBalle >= position.getX()
            && positionBalle.getX() - rayonBalle <= position.getX() + largeur
            && positionBalle.getY() + rayonBalle >= position.getY()
            && positionBalle.getY() - rayonBalle <= position.getY() + hauteur;
    }
    
    public Point getPosition() {
        return position;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Obstacle{" +
               "position=" + position +
               ", largeur=" + largeur +
               ", hauteur=" + hauteur +
               ", type='" + type + '\'' +
               '}';
    }
}
