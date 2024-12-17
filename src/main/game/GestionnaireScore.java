package main.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireScore {

    public record Score(String playerName, int totalScore) {}

    private final Map<String, List<Integer>> playerScores;

    public GestionnaireScore() {
        this.playerScores = new HashMap<>();
    }

    public void ajouterScore(String playerName, int level, int score) {
        playerScores.putIfAbsent(playerName, new ArrayList<>());

        List<Integer> scores = playerScores.get(playerName);

        while (scores.size() <= level) {
            scores.add(0);
        }

        scores.set(level, score);

        playerScores.put(playerName, scores);
    }

    public List<Score> getScoresTries() {
        List<Score> leaderboard = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : playerScores.entrySet()) {
            int totalScore = entry.getValue().stream().mapToInt(Integer::intValue).sum();
            leaderboard.add(new Score(entry.getKey(), totalScore));
        }

        leaderboard.sort((s1, s2) -> Integer.compare(s2.totalScore(), s1.totalScore())); 
        return leaderboard;
    }

    public void reinitialiserScores() {
        playerScores.clear();
    }

    public void afficherScores() {
        playerScores.forEach((playerName, scores) -> {
            System.out.print(playerName + " : ");
            for (int score : scores) {
                System.out.print(score + " ");
            }
            System.out.println("(Total: " + scores.stream().mapToInt(Integer::intValue).sum() + ")");
        });
    }
    public Map<String, List<Integer>> getPlayerScores() {
    return playerScores;
        }

}
