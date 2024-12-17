package main;

import main.geometry.Point;
import main.game.Balle;
import main.game.GestionnaireNiveaux;
import main.game.Trou;
import main.game.Niveau;
import main.game.GestionnaireScore;
import main.control.Controleur;
import main.ui.AfficheurCanvas;
import main.ui.GestionSouris;
import main.ui.MenuUI;
import main.ui.LeaderboardUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class Main extends Application {

    private GestionnaireNiveaux gestionnaireNiveaux;
    private GestionnaireScore gestionnaireScore;
    private Stage stage;
    private String selectedPlayer;
    private int[] levelScores; 

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        gestionnaireScore = new GestionnaireScore();

        showMenu();
    }

    private void startGameAsJoueur1() {
        selectedPlayer = "Joueur1";
        startNewGame();
    }

    private void startGameAsJoueur2() {
        selectedPlayer = "Joueur2";
        startNewGame();
    }

    private void startNewGame() {
    gestionnaireNiveaux = new GestionnaireNiveaux();
    levelScores = new int[gestionnaireNiveaux.getNiveaux().size()]; 
    loadLevel(); 
        }


    private void loadLevel() {
        Niveau niveau = gestionnaireNiveaux.getNiveauActuel();

        Balle balle = new Balle(niveau.getPositionBalleInitiale(), Color.BLUE, 10.0, 0.97);
        Trou trou = new Trou(niveau.getPositionTrou(), 12.0);

        AfficheurCanvas afficheur = new AfficheurCanvas(800, 600, balle, trou, niveau.getObstacles());

        Controleur controleur = new Controleur(balle, trou, afficheur, niveau.getObstacles(), gestionnaireScore, selectedPlayer, gestionnaireNiveaux);

        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setStyle("-fx-font-size: 16px; -fx-background-color: #FF5722; -fx-text-fill: white;");
        backToMenuButton.setOnAction(e -> showMenu());

        GestionSouris gestionSouris = new GestionSouris();

        afficheur.getCanvas().setOnMousePressed(e -> gestionSouris.gererAppuiSouris(e.getX(), e.getY()));
        afficheur.getCanvas().setOnMouseDragged(e -> afficheur.dessinerTrajectoire(gestionSouris.getDebutGlisse(), new Point(e.getX(), e.getY())));
        afficheur.getCanvas().setOnMouseReleased(e -> {
            gestionSouris.gererRelacheSouris(e.getX(), e.getY(), balle);
            controleur.gererMouvementBalle();
            controleur.gererNombreCoups();
        });

        Button finishLevelButton = new Button("Finish Level");
        finishLevelButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        finishLevelButton.setOnAction(e -> {
            
            gestionnaireNiveaux.passerAuNiveauSuivant();
            if (gestionnaireNiveaux.getNiveauActuelNumber() < levelScores.length) {
                loadLevel(); 
            } else {
                showLeaderboard(); 
            }
        });

        Pane racine = new Pane(afficheur.getCanvas(), backToMenuButton, finishLevelButton);
        finishLevelButton.setLayoutX(550);
        finishLevelButton.setLayoutY(20);
        backToMenuButton.setLayoutX(650);
        backToMenuButton.setLayoutY(20);

        Scene scene = new Scene(racine, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Mini Golf - Niveau " + (gestionnaireNiveaux.getNiveauActuelNumber() + 1));
        stage.show();
    }

    public void showLeaderboard() {
        LeaderboardUI.showLeaderboard(stage, gestionnaireScore); 
    }

    

    public void showMenu() {
        MenuUI.showMenu(stage, this::startGameAsJoueur1, this::startGameAsJoueur2, gestionnaireNiveaux, gestionnaireScore);
    }

    public static void main(String[] args) {
        launch();
    }
}
