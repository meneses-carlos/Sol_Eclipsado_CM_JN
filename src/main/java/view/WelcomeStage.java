package com.example.sol_eclipsado_cm_jn.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Window that represents the welcome screen of the application.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public class WelcomeStage extends Stage
{
    /**
     * Creates and configures the welcome stage.
     *
     * @throws IOException if the welcome FXML file cannot be loaded
     */
    public WelcomeStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sol_eclipsado_cm_jn/welcome-view.fxml")
        );

        Parent root = loader.load();
        Scene scene = new Scene(root);

        setTitle("Sol Eclipsado");
        setScene(scene);
        setResizable(false);
    }
}