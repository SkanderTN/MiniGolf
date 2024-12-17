package main.game;

import main.geometry.Point;

public class Trou {
    private Point position;     
    private double rayon;       
    private boolean termine;    

    public Trou(Point position, double rayon) {
        this.position = position;
        this.rayon = rayon;
        this.termine = false;
    }

    public boolean verifierCollision(Point positionBalle, double rayonBalle) {
        double distance = this.position.distanceEntre(positionBalle);
        
        if (distance <= this.rayon + rayonBalle) {
            this.termine = true; 
            return true;
        }
        return false;
    }

    public void reinitialiser() {
        this.termine = false;
    }

    public boolean estTermine() {
        return termine;
    }

    public Point getPosition() {
        return position;
    }

    public double getRayon() {
        return rayon;
    }

    @Override
    public String toString() {
        return "Trou{" +
               "position=" + position +
               ", rayon=" + rayon +
               ", termine=" + termine +
               '}';
    }
}
