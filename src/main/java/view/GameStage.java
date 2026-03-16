package com.example.sol_eclipsado_cm_jn.view;

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
 * @version 1.0
 * @since 1.0
 */
public class GameStage extends Stage
{
    public GameStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sol_eclipsado_cm_jn/game-view.fxml")
        );

        Parent root = loader.load();
        Scene scene = new Scene(root);

        setTitle("Game - Sol Eclipsado");
        setScene(scene);
        setResizable(false);
    }
}