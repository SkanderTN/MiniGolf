package main.game;

import main.geometry.Point;
import java.util.List;

public class Niveau {
    private final Point positionBalleInitiale;
    private final Point positionTrou;
    private final List<Obstacle> obstacles;
    private int numeroNiveau;

    public Niveau(Point positionBalleInitiale, Point positionTrou, List<Obstacle> obstacles, int numeroNiveau) {
        this.positionBalleInitiale = positionBalleInitiale;
        this.positionTrou = positionTrou;
        this.obstacles = obstacles;
        this.numeroNiveau = numeroNiveau;
    }

    public int getNumeroNiveau(){
        return numeroNiveau;
    }
    public Point getPositionBalleInitiale() {
        return positionBalleInitiale;
    }

    public Point getPositionTrou() {
        return positionTrou;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
