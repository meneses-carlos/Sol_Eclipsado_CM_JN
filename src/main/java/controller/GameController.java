package com.example.sol_eclipsado_cm_jn.controller;

import com.example.sol_eclipsado_cm_jn.model.SolarEclipseGame;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Controller for the main game screen.
 * It receives the game model and dynamically creates the input fields
 * required to represent the secret word.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.5
 * @since 1.0
 */
public class GameController
{
    /**
     * Locale used to transform letters to uppercase consistently.
     */
    private static final Locale SPANISH_LOCALE = new Locale("es", "ES");

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

        gameStatusLabel.setText("Escribe una letra válida en cada casilla.");
        wordLengthLabel.setText("La palabra tiene " + game.getSecretWord().length() + " letras.");
        createLetterFields(game.getSecretWord().length());
        focusFirstField();
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
            TextField letterField = buildLetterTextField(index);
            letterFields.add(letterField);
            lettersGridPane.add(letterField, index, 0);
        }
    }

    /**
     * Builds a single text field configured to represent one letter slot.
     *
     * @param fieldIndex position of the field inside the word
     * @return configured text field
     */
    private TextField buildLetterTextField(int fieldIndex)
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

        textField.setOnKeyTyped(event -> onHandleLetterFieldKeyTyped(event, fieldIndex));
        textField.setOnKeyPressed(event -> onHandleLetterFieldKeyPressed(event, fieldIndex));

        return textField;
    }

    /**
     * Handles the key typed event for a dynamic letter field.
     * It only allows one valid Spanish letter in each field.
     *
     * @param event key typed event
     * @param fieldIndex index of the field that received the event
     */
    private void onHandleLetterFieldKeyTyped(KeyEvent event, int fieldIndex)
    {
        String typedCharacter = event.getCharacter();

        if (typedCharacter == null || typedCharacter.isEmpty())
        {
            event.consume();
            return;
        }

        if (typedCharacter.charAt(0) == '\b' || typedCharacter.charAt(0) == 127)
        {
            event.consume();
            return;
        }

        if (!isValidSpanishLetter(typedCharacter))
        {
            gameStatusLabel.setText("Solo se permiten letras del alfabeto español.");
            event.consume();
            return;
        }

        TextField currentField = letterFields.get(fieldIndex);
        currentField.setText(typedCharacter.toUpperCase(SPANISH_LOCALE));
        moveToNextField(fieldIndex);

        gameStatusLabel.setText("Letra registrada correctamente.");
        event.consume();
    }

    /**
     * Handles special key actions for a dynamic letter field.
     * It supports backspace navigation and left-right movement.
     *
     * @param event key pressed event
     * @param fieldIndex index of the field that received the event
     */
    private void onHandleLetterFieldKeyPressed(KeyEvent event, int fieldIndex)
    {
        TextField currentField = letterFields.get(fieldIndex);

        if (event.getCode() == KeyCode.BACK_SPACE)
        {
            if (!currentField.getText().isEmpty())
            {
                currentField.clear();
            }
            else if (fieldIndex > 0)
            {
                TextField previousField = letterFields.get(fieldIndex - 1);
                previousField.clear();
                previousField.requestFocus();
            }

            gameStatusLabel.setText("Casilla actualizada.");
            event.consume();
            return;
        }

        if (event.getCode() == KeyCode.LEFT && fieldIndex > 0)
        {
            letterFields.get(fieldIndex - 1).requestFocus();
            event.consume();
            return;
        }

        if (event.getCode() == KeyCode.RIGHT && fieldIndex < letterFields.size() - 1)
        {
            letterFields.get(fieldIndex + 1).requestFocus();
            event.consume();
        }
    }

    /**
     * Checks whether a typed character is a valid Spanish alphabet letter.
     *
     * @param character typed character to validate
     * @return true if the character is valid, false otherwise
     */
    private boolean isValidSpanishLetter(String character)
    {
        return character.matches("[A-Za-zÁÉÍÓÚáéíóúÑñÜü]");
    }

    /**
     * Moves the focus to the next available field if it exists.
     *
     * @param currentIndex current field index
     */
    private void moveToNextField(int currentIndex)
    {
        if (currentIndex < letterFields.size() - 1)
        {
            letterFields.get(currentIndex + 1).requestFocus();
        }
    }

    /**
     * Focuses the first generated field if at least one field exists.
     */
    private void focusFirstField()
    {
        if (!letterFields.isEmpty())
        {
            letterFields.get(0).requestFocus();
        }
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