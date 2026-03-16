package com.example.sol_eclipsado_cm_jn.view;

import com.example.sol_eclipsado_cm_jn.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Window that represents the main game screen.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.4
 * @since 1.0
 */
public class GameStage extends Stage
{
    /**
     * Controller associated with the game view.
     */
    private final GameController gameController;

    /**
     * Creates and configures the game stage.
     *
     * @throws IOException if the game FXML file cannot be loaded
     */
    public GameStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sol_eclipsado_cm_jn/game-view.fxml")
        );

        Parent root = loader.load();
        gameController = loader.getController();

        Scene scene = new Scene(root);

        setTitle("Sol Eclipsado - Juego");
        setScene(scene);
        setResizable(false);
    }

    /**
     * Returns the controller associated with the game stage.
     *
     * @return game controller
     */
    public GameController getGameController()
    {
        return gameController;
    }
}