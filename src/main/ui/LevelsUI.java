package main.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.game.GestionnaireNiveaux;

public class LevelsUI {

    public static void showLevels(Stage stage, GestionnaireNiveaux gestionnaireNiveaux, Runnable onLevelSelected) {
        // Create a GridPane to layout the level buttons
        GridPane grid = new GridPane();
        grid.setHgap(10);  // Horizontal spacing between buttons
        grid.setVgap(10);  // Vertical spacing between buttons

        // Assuming you have a total of 5 levels
        int totalLevels = 2;
        for (int i = 1; i <= totalLevels; i++) {
            final int levelIndex = i;  // Make i final (or effectively final)
            Button levelButton = new Button("Level " + i);
            levelButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px;");
            
            // When the button is clicked, set the NiveauActuel and trigger onLevelSelected callback
            levelButton.setOnAction(e -> {
                gestionnaireNiveaux.setNiveauActuel(levelIndex-1);  // Set the current level
                onLevelSelected.run();  // Trigger the callback to start the selected level
            });
            
            // Place each button in the grid
            grid.add(levelButton, (i - 1) % 3, (i - 1) / 3);  // Arrange buttons in rows
        }

        // Layout for the levels scene
        Scene scene = new Scene(grid, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Select Level");
        stage.show();
    }
}
