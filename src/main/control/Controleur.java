package main.control;

import main.game.Balle;
import main.game.GestionnaireScore;
import main.game.Trou;
import main.game.Obstacle;
import main.ui.AfficheurCanvas;
import main.game.GestionnaireNiveaux;

import java.util.List;

public class Controleur {
    private Balle balle;
    private Trou trou;
    private AfficheurCanvas afficheur;
    private List<Obstacle> obstacles;
    private boolean partieTerminee;
    private int coups;
    private GestionnaireScore gestionnaireScore;
    private String nomJoueur;
    private GestionnaireNiveaux gestionnaireNiveaux; 

    public Controleur(Balle balle, Trou trou, AfficheurCanvas afficheur, List<Obstacle> obstacles, 
                      GestionnaireScore gestionnaireScore, String nomJoueur, GestionnaireNiveaux gestionnaireNiveaux) {
        this.balle = balle;
        this.trou = trou;
        this.afficheur = afficheur;
        this.obstacles = obstacles;
        this.partieTerminee = false;
        this.coups = 0;
        this.gestionnaireScore = gestionnaireScore;
        this.nomJoueur = nomJoueur;
        this.gestionnaireNiveaux = gestionnaireNiveaux; 
    }

    public boolean getPartieTerminee() {
        return this.partieTerminee;
    }

    public void verifierCollision() {
        // Vérifier si la balle est dans le trou
        if (trou.verifierCollision(balle.getPosition(), balle.getRayon())) {
            partieTerminee = true;
            System.out.println("Félicitations ! Vous avez terminé le niveau.");
            enregistrerScore();
        }

        // Vérifier les collisions avec chaque obstacle
        for (Obstacle obstacle : obstacles) {
            if (obstacle.verifierCollision(balle)) {
                obstacle.appliquerEffet(balle); // Appliquer l'effet de l'obstacle
            }
        }
    }

    public void gererMouvementBalle() {
        new Thread(() -> {
            while (balle.getVitesse() > 0 && !partieTerminee) {
                balle.deplacer();
                verifierCollision();
                afficheur.dessiner();

                try {
                    Thread.sleep(45); // Mise à jour toutes les 50ms
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void gererNombreCoups() {
        coups++;
    }

    public int getCoups() {
        return coups;
    }

    public void enregistrerScore() {
        // Use the correct current level from GestionnaireNiveaux
        int currentLevel = gestionnaireNiveaux.getNiveauActuelNumber();
    
        // Debug: Output the current level and current score
        System.out.println("Enregistrement du score pour le niveau: " + currentLevel);
        System.out.println("Nombre de coups actuel: " + coups);
    
        // Pass only the score for the current level to the score manager
        gestionnaireScore.ajouterScore(nomJoueur, currentLevel, coups); // Pass level and score
    
        // Debug: Confirm that the score is being saved correctly
        System.out.println("Score enregistré pour " + nomJoueur + ": " + coups + " coups sur le niveau " + currentLevel);
    }
    
    
    

    public void reinitialiserJeu() {
        partieTerminee = false;
        balle.resetPosition();
        trou.reinitialiser();
        coups = 0;
        afficheur.dessiner();
    }

    
}
