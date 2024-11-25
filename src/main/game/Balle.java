package main.game;

import main.geometry.Point;

import javafx.scene.paint.Color;

public class Balle {
    private Point position;     // Position actuelle
    private Point positionInitiale; // Position départ
    private double vitesse;    
    private double angle;       // en degrés
    private Color couleur;      
    private double frottement;  // Facteur de frottement (0-1)
    private double rayon;       

    // Constructeur
    public Balle(Point positionInitiale, Color couleur, double rayon, double frottement) {
        this.position = positionInitiale;
        this.positionInitiale = new Point(positionInitiale.getX(), positionInitiale.getY()); // Copie
        this.vitesse = 0;
        this.angle = 0;
        this.couleur = couleur;
        this.rayon = rayon;
        this.frottement = frottement;
    }

    // Méthodes
    public void deplacer() {
        // Mise à jour de la position en fonction de la vitesse et de l'angle
        double deltaX = vitesse * Math.cos(Math.toRadians(angle));
        double deltaY = vitesse * Math.sin(Math.toRadians(angle));
        position.setX(position.getX() + deltaX);
        position.setY(position.getY() + deltaY);

        // Appliquer le frottement
        vitesse *= frottement; // Réduction de la vitesse selon le facteur de frottement
        if (vitesse < 0.01) vitesse = 0; // Arrêter la balle si la vitesse est négligeable
    }

    public void calculerTrajectoire(double angle, double force) {
        this.angle = angle;
        this.vitesse = force; // Fixer la vitesse en fonction de la force appliquée
    }

    public void arreter() {
        this.vitesse = 0;
    }

    public void resetPosition() {
        // Réinitialiser la position de la balle à sa position initiale
        this.position.setX(positionInitiale.getX());
        this.position.setY(positionInitiale.getY());
        this.vitesse = 0;
    }

    // Getters et setters
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
