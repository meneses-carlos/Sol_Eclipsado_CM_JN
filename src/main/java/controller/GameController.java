package com.example.sol_eclipsado_cm_jn.controller;

import com.example.sol_eclipsado_cm_jn.model.SolarEclipseGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the main game screen.
 * It receives the game model from the welcome screen and updates the game view.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.3
 * @since 1.0
 */
public class GameController
{
    /**
     * Current game model used by the game screen.
     */
    private SolarEclipseGame game;

    /**
     * Label that displays the general state of the game screen.
     */
    @FXML
    private Label gameStatusLabel;

    /**
     * Label that displays the number of letters of the secret word.
     */
    @FXML
    private Label wordLengthLabel;

    /**
     * Initializes the controller after the FXML view has been loaded.
     */
    @FXML
    public void initialize()
    {
        gameStatusLabel.setText("Esperando información del juego...");
        wordLengthLabel.setText("");
    }

    /**
     * Receives the game model from the welcome screen.
     *
     * @param game current game model
     */
    public void setGame(SolarEclipseGame game)
    {
        this.game = game;
        updateView();
    }

    /**
     * Updates the game view using the current model data.
     */
    private void updateView()
    {
        if (game == null)
        {
            gameStatusLabel.setText("No se pudo cargar la información del juego.");
            wordLengthLabel.setText("");
            return;
        }

        gameStatusLabel.setText("La palabra secreta fue cargada correctamente.");
        wordLengthLabel.setText("La palabra tiene " + game.getSecretWord().length() + " letras.");
    }

    /**
     * Returns the current game model.
     *
     * @return current game model
     */
    public SolarEclipseGame getGame()
    {
        return game;
    }
}