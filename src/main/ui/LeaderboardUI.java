package main.ui;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.game.GestionnaireScore;
import main.game.GestionnaireScore.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;


public class LeaderboardUI {

    public static void showLeaderboard(Stage stage, GestionnaireScore gestionnaireScore) {
        TableView<PlayerScore> tableView = new TableView<>();

        TableColumn<PlayerScore, String> playerNameColumn = new TableColumn<>("Player");
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<PlayerScore, String> levelScoresColumn = new TableColumn<>("Level Scores");
        levelScoresColumn.setCellValueFactory(new PropertyValueFactory<>("levelScores"));

        TableColumn<PlayerScore, Integer> totalScoreColumn = new TableColumn<>("Total Score");
        totalScoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        tableView.getColumns().add(playerNameColumn);
        tableView.getColumns().add(levelScoresColumn);
        tableView.getColumns().add(totalScoreColumn);

        ObservableList<PlayerScore> data = FXCollections.observableArrayList();

        Map<String, List<Integer>> playerScores = gestionnaireScore.getPlayerScores();
        for (Score score : gestionnaireScore.getScoresTries()) {
            String playerName = score.playerName();
            List<Integer> levelScores = playerScores.get(playerName);
            int totalScore = score.totalScore();

            data.add(new PlayerScore(playerName, levelScores, totalScore));
        }

        tableView.setItems(data);

        Scene scene = new Scene(tableView, 600, 400);

        stage.setScene(scene);
        stage.setTitle("Leaderboard");
        stage.show();
    }

    public static class PlayerScore {
        private String playerName;
        private List<Integer> levelScores;
        private int totalScore;

        public PlayerScore(String playerName, List<Integer> levelScores, int totalScore) {
            this.playerName = playerName;
            this.levelScores = levelScores;
            this.totalScore = totalScore;
        }

        public String getPlayerName() {
            return playerName;
        }

        public List<Integer> getLevelScores() {
            return levelScores;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public String getLevelScoresAsString() {
            return String.join(", ", levelScores.stream().map(String::valueOf).toArray(String[]::new));
        }
    }
}
