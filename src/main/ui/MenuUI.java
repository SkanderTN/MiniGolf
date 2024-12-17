package main.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import main.game.GestionnaireScore;
import main.game.GestionnaireNiveaux;

public class MenuUI {

    private static String selectedPlayer;

    public static void showMenu(Stage stage, Runnable onStartGameForJoueur1, Runnable onStartGameForJoueur2, GestionnaireNiveaux gestionnaireNiveaux, GestionnaireScore gestionnaireScore) {
        Button joueur1Button = new Button("Joueur1");
        joueur1Button.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        joueur1Button.setOnAction(e -> {
            selectedPlayer = "Joueur1";
            onStartGameForJoueur1.run();
        });

        Button joueur2Button = new Button("Joueur2");
        joueur2Button.setStyle("-fx-font-size: 18px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        joueur2Button.setOnAction(e -> {
            selectedPlayer = "Joueur2";
            onStartGameForJoueur2.run();
        });

        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #FFC107; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        leaderboardButton.setOnAction(e -> showLeaderboard(stage, gestionnaireScore));

        Button quitterButton = new Button("Quitter");
        quitterButton.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        quitterButton.setOnAction(e -> System.exit(0)); // Exit the application

        VBox layout = new VBox(20, joueur1Button, joueur2Button, leaderboardButton, quitterButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 50px;");

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Mini Golf - Main Menu");
        stage.show();
    }

    private static void showLeaderboard(Stage stage, GestionnaireScore gestionnaireScore) {
        LeaderboardUI.showLeaderboard(stage, gestionnaireScore);
    }

    public static String getSelectedPlayer() {
        return selectedPlayer;
    }
}
