package com.example.sol_eclipsado_cm_jn.controller;

import com.example.sol_eclipsado_cm_jn.model.SolarEclipseGame;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for the main game screen.
 * It receives the game model and dynamically creates the input fields
 * required to represent the secret word.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.4
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
     * Grid container used to place the generated letter fields.
     */
    @FXML
    private GridPane lettersGridPane;

    /**
     * Dynamic list of text fields created for the secret word.
     */
    private final List<TextField> letterFields;

    /**
     * Creates the controller and initializes the internal list of fields.
     */
    public GameController()
    {
        letterFields = new ArrayList<>();
    }

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
     * Receives the game model from the welcome screen and refreshes the game view.
     *
     * @param game current game model
     */
    public void setGame(SolarEclipseGame game)
    {
        this.game = game;
        updateView();
    }

    /**
     * Updates the visible information of the view based on the current model.
     */
    private void updateView()
    {
        if (game == null)
        {
            gameStatusLabel.setText("No se pudo cargar la información del juego.");
            wordLengthLabel.setText("");
            clearLetterFields();
            return;
        }

        gameStatusLabel.setText("Ingresa una letra en cada casilla.");
        wordLengthLabel.setText("La palabra tiene " + game.getSecretWord().length() + " letras.");
        createLetterFields(game.getSecretWord().length());
    }

    /**
     * Dynamically creates one text field for each letter of the secret word.
     *
     * @param wordLength number of letters in the secret word
     */
    private void createLetterFields(int wordLength)
    {
        clearLetterFields();

        for (int index = 0; index < wordLength; index++)
        {
            TextField letterField = buildLetterTextField();
            letterFields.add(letterField);
            lettersGridPane.add(letterField, index, 0);
        }
    }

    /**
     * Builds a single text field configured to represent one letter slot.
     *
     * @return configured text field
     */
    private TextField buildLetterTextField()
    {
        TextField textField = new TextField();
        textField.setPrefWidth(42);
        textField.setPrefHeight(42);
        textField.setMaxWidth(42);
        textField.setAlignment(Pos.CENTER);
        textField.setPromptText("");
        textField.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;"
        );

        return textField;
    }

    /**
     * Clears the generated text fields from both the grid and the internal list.
     */
    private void clearLetterFields()
    {
        lettersGridPane.getChildren().clear();
        letterFields.clear();
    }

    /**
     * Returns an unmodifiable view of the generated letter fields.
     *
     * @return generated letter fields
     */
    public List<TextField> getLetterFields()
    {
        return Collections.unmodifiableList(letterFields);
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