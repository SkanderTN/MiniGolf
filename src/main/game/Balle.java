package main.game;

import main.geometry.Point;
import javafx.scene.paint.Color;

public class Balle {
    private Point positionInitiale; // Position départ
    private Point position;     // Position actuelle
    private double vitesse;    
    private double angle;       // en degrés
    private Color couleur;      
    private double frottement;  // facteur de frottement (0-1)
    private double rayon;       

    public Balle(Point positionInitiale, Color couleur, double rayon, double frottement) {
        this.position = positionInitiale;
        this.positionInitiale = new Point(positionInitiale.getX(), positionInitiale.getY()); 
        this.vitesse = 0;
        this.angle = 0;
        this.couleur = couleur;
        this.rayon = rayon;
        this.frottement = frottement;
    }

    public void deplacer() {
        double deltaX = vitesse * Math.cos(Math.toRadians(angle));
        double deltaY = vitesse * Math.sin(Math.toRadians(angle));
        position.setX(position.getX() + deltaX);
        position.setY(position.getY() + deltaY);

        vitesse *= frottement; 
        if (vitesse < 0.01) vitesse = 0; // Arreter la balle si la vitesse est négligeable
    }

    public void calculerTrajectoire(double angle, double force) {
        this.angle = angle;
        this.vitesse = force; 
    }

    public void arreter() {
        this.vitesse = 0;
    }

    public void resetPosition() {
        this.position.setX(positionInitiale.getX());
        this.position.setY(positionInitiale.getY());
        this.vitesse = 0;
    }

    public Point getPosition() {
        return position;
    }

    public Color getCouleur() {
        return couleur;
    }

    public double getVitesse() {
        return vitesse;
    }

    public double getRayon() {
        return rayon;
    }

    public double getAngle() {
        return angle;
    }

    public double getFrottement() {
        return frottement;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setFrottement(double frottement) {
        this.frottement = frottement;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public String toString() {
        return "Balle{" +
               "position=(" + position.getX() + ", " + position.getY() + ")" +
               ", vitesse=" + vitesse +
               ", angle=" + angle +
               ", rayon=" + rayon +
               ", couleur=" + couleur +
               '}';
    }
}
