package com.example.sol_eclipsado_cm_jn.controller;

import com.example.sol_eclipsado_cm_jn.model.SolarEclipseGame;
import javafx.fxml.FXML;
/**
 * Controller for the welcome screen of the Solar Eclipse game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public class WelcomeController
{
    private SolarEclipseGame game;
    /**
     * Initializes the welcome view controller after FXML loading.
     */
    public WelcomeController()
    {
        game = new SolarEclipseGame();
    }

    @FXML
    public void initialize()
    {
    }
}