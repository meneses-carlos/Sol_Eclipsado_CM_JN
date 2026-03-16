package com.example.sol_eclipsado_cm_jn.model;

/**
 * Defines the base contract for the Solar Eclipse game model.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.2
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
}