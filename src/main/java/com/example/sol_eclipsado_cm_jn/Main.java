package com.example.sol_eclipsado_cm_jn;

import com.example.sol_eclipsado_cm_jn.view.WelcomeStageHolder;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Main JavaFX entry point for the Solar Eclipse game application.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application
{
    /**
     * Launches the initial application window.
     *
     * @param primaryStage main stage provided by JavaFX runtime
     * @throws Exception if the welcome view cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        WelcomeStageHolder.getInstance().show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}