package main.game;

import main.geometry.Point;
import java.util.List;
import java.util.ArrayList;


public class GestionnaireNiveaux {
    private final List<Niveau> niveaux;
    private int niveauActuel;

    public GestionnaireNiveaux() {
        this.niveaux = new ArrayList<>();
        this.niveauActuel = 0;
        initialiserNiveaux(); 
    }
    
    public List<Niveau> getNiveaux(){
        return niveaux;
    }
    public void ajouterNiveau(Niveau niveau) {
        niveaux.add(niveau);
    }

    public Niveau getNiveauActuel() {
        return niveaux.get(niveauActuel);
    }
    public int getNiveauActuelNumber(){
        return niveauActuel;
    }

    public void setNiveauActuel (int n){
        this.niveauActuel = n;
    }

    public boolean passerAuNiveauSuivant() {
        if (niveauActuel < niveaux.size() - 1) {
            niveauActuel++;
            return true;
        }
        return false; 
    }

    public void reinitialiser() {
        niveauActuel = 0;
    }

    private void initialiserNiveaux() {
        // Niveau 1: Added a small wall in the middle
        Niveau niveau1 = new Niveau(
            new Point(100, 100),  // Position initiale de la balle
            new Point(700, 500),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"), // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"), // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),  // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"), // Mur droit
                new Mur(new Point(400, 200), 10.0, 200.0, "vertical")  // Small wall in the middle
            ),
            0
        );
        ajouterNiveau(niveau1);
    
        // Niveau 2: Added sand and small random walls
        Niveau niveau2 = new Niveau(
            new Point(150, 150),  // Position initiale de la balle
            new Point(600, 400),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),  // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"), // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),     // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),   // Mur droit
                new Sable(new Point(350, 300), 200, 50),                // Sable in the way of the ball to the hole
                new Mur(new Point(200, 200), 100.0, 10.0, "horizontal"), // Small random wall 1
                new Mur(new Point(500, 250), 50.0, 10.0, "horizontal"),  // Small random wall 2
                new Mur(new Point(550, 450), 100.0, 10.0, "horizontal")  // Small random wall 3
            ),
            1
        );
        ajouterNiveau(niveau2);
        // Niveau 3: Complex level with walls, sand, and more challenges
        Niveau niveau3 = new Niveau(
            new Point(100, 100),  // Position initiale de la balle (new position)
            new Point(700, 500),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),   // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"),  // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),      // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),    // Mur droit

                // Adding challenging walls that form barriers
                new Mur(new Point(300, 200), 150.0, 10.0, "horizontal"), // Horizontal wall near the center
                new Mur(new Point(500, 300), 150.0, 10.0, "horizontal"), // Another horizontal wall crossing the center
                new Mur(new Point(200, 400), 10.0, 200.0, "vertical"),   // Vertical wall blocking path near ball
                new Mur(new Point(600, 100), 10.0, 400.0, "vertical"),   // Vertical wall near the hole

                // Adding sand in an annoying place that slows the ball
                new Sable(new Point(350, 350), 100, 50),                 // Sable in the middle, annoying obstacle
                new Sable(new Point(550, 250), 100, 50),                 // Sable near the hole, creating a challenge

                // A few more small random walls
                new Mur(new Point(400, 400), 50.0, 10.0, "horizontal"),  // Horizontal wall blocking path near the hole
                new Mur(new Point(450, 150), 10.0, 100.0, "vertical")    // Small vertical wall near the top-right corner
                 ),
                2
                );
                ajouterNiveau(niveau3);
        
        }
}
