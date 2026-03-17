package com.example.sol_eclipsado_cm_jn.model;

import java.util.List;
import java.util.Locale;

/**
 * Defines the base contract for the Solar Eclipse game model.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.8
 * @since 1.0
 */
public interface ISolarEclipseGame
{
    /**
     * Sets the secret word for the current game.
     *
     * @param secretWord word to be guessed by the player
     */
    void setSecretWord(String secretWord);

    /**
     * Returns the current secret word.
     *
     * @return current secret word
     */
    String getSecretWord();

    /**
     * Returns the current number of errors.
     *
     * @return current error count
     */
    int getErrors();

    /**
     * Returns the number of used help actions.
     *
     * @return used help count
     */
    int getUsedHelps();

    /**
     * Checks whether the entered letter matches the secret word
     * at the specified position.
     *
     * @param index position to validate
     * @param enteredLetter entered letter
     * @return true if the entered letter is correct for the position, false otherwise
     */
    boolean isLetterCorrectAt(int index, String enteredLetter);

    /**
     * Counts how many entered letters are correct in their respective positions.
     *
     * @param enteredLetters letters currently entered by the user
     * @return number of correct letters
     */
    int countCorrectLetters(List<String> enteredLetters);

    /**
     * Checks whether the whole secret word has been completed correctly.
     *
     * @param enteredLetters letters currently entered by the user
     * @return true if the whole word matches, false otherwise
     */
    boolean isSecretWordCompleted(List<String> enteredLetters);

    /**
     * Increases the number of committed errors by one.
     */
    void registerError();

    /**
     * Returns the number of attempts remaining in the game.
     *
     * @return remaining attempts
     */
    int getRemainingAttempts();

    /**
     * Checks whether the maximum number of errors has been reached.
     *
     * @return true if the game is lost, false otherwise
     */
    boolean isGameLost();

    /**
     * Returns the eclipse percentage based on the current number of errors.
     *
     * @return eclipse percentage
     */
    int getEclipsePercentage();
}
