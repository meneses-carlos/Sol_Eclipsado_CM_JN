package com.example.sol_eclipsado_cm_jn.controller;

import com.example.sol_eclipsado_cm_jn.model.SolarEclipseGame;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Controller for the main game screen.
 * It receives the game model, creates the dynamic input fields,
 * evaluates each entered letter, counts errors per incorrect letter
 * and updates the eclipse visualization.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.8
 * @since 1.0
 */
public class GameController
{
    /**
     * Locale used to transform letters to uppercase consistently.
     */
    private static final Locale SPANISH_LOCALE = new Locale("es", "ES");

    /**
     * Style used for neutral fields.
     */
    private static final String NEUTRAL_FIELD_STYLE =
            "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 4;" +
                    "-fx-background-radius: 4;";

    /**
     * Style used for correct fields.
     */
    private static final String CORRECT_FIELD_STYLE =
            "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #2e7d32;" +
                    "-fx-background-color: #c8e6c9;" +
                    "-fx-border-radius: 4;" +
                    "-fx-background-radius: 4;";

    /**
     * Style used for incorrect fields.
     */
    private static final String INCORRECT_FIELD_STYLE =
            "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #c62828;" +
                    "-fx-background-color: #ffcdd2;" +
                    "-fx-border-radius: 4;" +
                    "-fx-background-radius: 4;";

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
     * Label that displays the remaining attempts.
     */
    @FXML
    private Label remainingAttemptsLabel;

    /**
     * Label that displays the eclipse percentage.
     */
    @FXML
    private Label eclipsePercentageLabel;

    /**
     * Image that displays the current eclipse phase.
     */
    @FXML
    private ImageView eclipseImageView;

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
        gameStatusLabel.setText("Esperando informacion del juego...");
        wordLengthLabel.setText("");
        remainingAttemptsLabel.setText("");
        eclipsePercentageLabel.setText("Eclipse del sol: 0%");
        updateEclipseImage(0);
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
            gameStatusLabel.setText("No se pudo cargar la informacion del juego.");
            wordLengthLabel.setText("");
            remainingAttemptsLabel.setText("");
            eclipsePercentageLabel.setText("Eclipse del sol: 0%");
            updateEclipseImage(0);
            clearLetterFields();
            return;
        }

        gameStatusLabel.setStyle("-fx-text-fill: black;");
        gameStatusLabel.setText("Escribe una letra valida en cada casilla.");
        wordLengthLabel.setText("La palabra tiene " + game.getSecretWord().length() + " letras.");
        updateRemainingAttemptsLabel();
        updateEclipseVisualization();
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
        textField.setStyle(NEUTRAL_FIELD_STYLE);

        textField.setOnKeyTyped(event -> onHandleLetterFieldKeyTyped(event, fieldIndex));
        textField.setOnKeyPressed(event -> onHandleLetterFieldKeyPressed(event, fieldIndex));

        return textField;
    }

    /**
     * Handles the key typed event for a dynamic letter field.
     * It only allows one valid Spanish letter in each field and
     * counts an error immediately if the entered letter is incorrect.
     *
     * @param event key typed event
     * @param fieldIndex index of the field that received the event
     */
    private void onHandleLetterFieldKeyTyped(KeyEvent event, int fieldIndex)
    {
        if (game != null && game.isGameLost())
        {
            event.consume();
            return;
        }

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
            gameStatusLabel.setStyle("-fx-text-fill: #c62828;");
            gameStatusLabel.setText("Solo se permiten letras del alfabeto espanol.");
            event.consume();
            return;
        }

        TextField currentField = letterFields.get(fieldIndex);
        String enteredLetter = typedCharacter.toUpperCase(SPANISH_LOCALE);

        currentField.setText(enteredLetter);

        boolean isCorrect = game.isLetterCorrectAt(fieldIndex, enteredLetter);
        evaluateCurrentField(fieldIndex);

        if (isCorrect)
        {
            updateProgressStatus();

            if (!game.isGameLost())
            {
                moveToNextField(fieldIndex);
            }
        }
        else
        {
            game.registerError();
            updateRemainingAttemptsLabel();
            updateEclipseVisualization();

            if (game.isGameLost())
            {
                gameStatusLabel.setStyle("-fx-text-fill: #c62828;");
                gameStatusLabel.setText("Has perdido. Se agotaron los intentos.");
                disableAllLetterFields();
                event.consume();
                return;
            }

            gameStatusLabel.setStyle("-fx-text-fill: #c62828;");
            gameStatusLabel.setText("Letra incorrecta. Intentos restantes: " + game.getRemainingAttempts() + ".");
            currentField.requestFocus();
        }

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
        if (game != null && game.isGameLost())
        {
            event.consume();
            return;
        }

        TextField currentField = letterFields.get(fieldIndex);

        if (event.getCode() == KeyCode.BACK_SPACE)
        {
            if (!currentField.isEditable())
            {
                event.consume();
                return;
            }

            if (!currentField.getText().isEmpty())
            {
                currentField.clear();
                applyNeutralStyle(currentField);
            }
            else if (fieldIndex > 0)
            {
                TextField previousField = letterFields.get(fieldIndex - 1);

                if (previousField.isEditable())
                {
                    previousField.clear();
                    applyNeutralStyle(previousField);
                }

                previousField.requestFocus();
            }

            updateProgressStatus();
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
     * Evaluates the current field against the secret word and updates its visual style.
     *
     * @param fieldIndex position of the field to evaluate
     */
    private void evaluateCurrentField(int fieldIndex)
    {
        TextField currentField = letterFields.get(fieldIndex);
        String enteredLetter = currentField.getText();

        if (enteredLetter.isBlank())
        {
            applyNeutralStyle(currentField);
            return;
        }

        if (game.isLetterCorrectAt(fieldIndex, enteredLetter))
        {
            applyCorrectStyle(currentField);
            currentField.setEditable(false);
        }
        else
        {
            applyIncorrectStyle(currentField);
        }
    }

    /**
     * Updates the progress message without counting additional errors.
     */
    private void updateProgressStatus()
    {
        List<String> enteredLetters = getEnteredLetters();
        int correctLetters = game.countCorrectLetters(enteredLetters);

        updateRemainingAttemptsLabel();
        updateEclipseVisualization();

        if (game.isGameLost())
        {
            gameStatusLabel.setStyle("-fx-text-fill: #c62828;");
            gameStatusLabel.setText("Has perdido. Se agotaron los intentos.");
            disableAllLetterFields();
            return;
        }

        if (game.isSecretWordCompleted(enteredLetters))
        {
            gameStatusLabel.setStyle("-fx-text-fill: #2e7d32;");
            gameStatusLabel.setText("Correcto. Has completado la palabra secreta.");
            disableAllLetterFields();
            return;
        }

        if (areAllFieldsFilled())
        {
            gameStatusLabel.setStyle("-fx-text-fill: black;");
            gameStatusLabel.setText("Hay letras incorrectas. Corrige las casillas en rojo.");
            return;
        }

        gameStatusLabel.setStyle("-fx-text-fill: black;");
        gameStatusLabel.setText("Letras correctas en posicion: " + correctLetters + " de " + letterFields.size() + ".");
    }

    @FXML
    private void onHandleHelpButton()
    {
        if (!game.canUseHelp())
        {
            gameStatusLabel.setText("Ya usaste las 3 ayudas.");
            return;
        }

        List<String> enteredLetters = getEnteredLetters();
        int index = game.useHelp(enteredLetters);

        if (index == -1)
        {
            gameStatusLabel.setText("No hay letras para revelar.");
            return;
        }

        String correctLetter = String.valueOf(game.getSecretWord().charAt(index));

        TextField field = letterFields.get(index);
        field.setText(correctLetter);
        applyCorrectStyle(field);
        field.setEditable(false);

        updateProgressStatus();
    }

    /**
     * Updates the label that shows the remaining attempts.
     */
    private void updateRemainingAttemptsLabel()
    {
        remainingAttemptsLabel.setText("Intentos restantes: " + game.getRemainingAttempts());
    }

    /**
     * Updates the eclipse visual representation and the eclipse percentage label.
     */
    private void updateEclipseVisualization()
    {
        int errors = game.getErrors();
        int eclipsePercentage = game.getEclipsePercentage();

        updateEclipseImage(errors);
        eclipsePercentageLabel.setText("Eclipse del sol: " + eclipsePercentage + "%");
    }

    /**
     * Loads the image associated with the current eclipse phase.
     *
     * @param phaseNumber current phase number
     */
    private void updateEclipseImage(int phaseNumber)
    {
        String imagePath = "/com/example/sol_eclipsado_cm_jn/images/FASE " + phaseNumber + ".png";

        try
        {
            if (getClass().getResource(imagePath) != null)
            {
                Image phaseImage = new Image(getClass().getResource(imagePath).toExternalForm());
                eclipseImageView.setImage(phaseImage);
            }
            else
            {
                eclipseImageView.setImage(null);
                System.err.println("No se encontro la imagen: " + imagePath);
            }
        }
        catch (Exception exception)
        {
            eclipseImageView.setImage(null);
            System.err.println("No se pudo cargar la imagen: " + imagePath);
        }
    }

    /**
     * Disables all dynamic text fields.
     */
    private void disableAllLetterFields()
    {
        for (TextField letterField : letterFields)
        {
            letterField.setDisable(true);
        }
    }

    /**
     * Returns the letters currently entered by the user.
     *
     * @return list of entered letters
     */
    private List<String> getEnteredLetters()
    {
        List<String> enteredLetters = new ArrayList<>();

        for (TextField letterField : letterFields)
        {
            enteredLetters.add(letterField.getText());
        }

        return enteredLetters;
    }

    /**
     * Checks whether all generated fields currently contain a letter.
     *
     * @return true if all fields are filled, false otherwise
     */
    private boolean areAllFieldsFilled()
    {
        for (TextField letterField : letterFields)
        {
            if (letterField.getText().isBlank())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Applies the neutral style to a field.
     *
     * @param textField field to style
     */
    private void applyNeutralStyle(TextField textField)
    {
        textField.setStyle(NEUTRAL_FIELD_STYLE);
    }

    /**
     * Applies the correct style to a field.
     *
     * @param textField field to style
     */
    private void applyCorrectStyle(TextField textField)
    {
        textField.setStyle(CORRECT_FIELD_STYLE);
    }

    /**
     * Applies the incorrect style to a field.
     *
     * @param textField field to style
     */
    private void applyIncorrectStyle(TextField textField)
    {
        textField.setStyle(INCORRECT_FIELD_STYLE);
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
