package com.example.sol_eclipsado_cm_jn.model;

/**
 * Represents the main state of the Solar Eclipse game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.2
 * @since 1.0
 * @see ISolarEclipseGame
 */
public class SolarEclipseGame implements ISolarEclipseGame
{
    /**
     * Secret word to be guessed during the game.
     */
    private String secretWord;

    /**
     * Number of incorrect attempts made by the player.
     */
    private int errors;

    /**
     * Number of help actions already used.
     */
    private int usedHelps;

    /**
     * Creates a new game model with default values.
     */
    public SolarEclipseGame()
    {
        secretWord = "";
        errors = 0;
        usedHelps = 0;
    }

    /**
     * Sets the secret word for the current game.
     *
     * @param secretWord word to be guessed
     */
    @Override
    public void setSecretWord(String secretWord)
    {
        this.secretWord = secretWord.trim();
    }

    /**
     * Returns the secret word of the current game.
     *
     * @return current secret word
     */
    @Override
    public String getSecretWord()
    {
        return secretWord;
    }

    /**
     * Returns the current number of errors.
     *
     * @return error count
     */
    @Override
    public int getErrors()
    {
        return errors;
    }

    /**
     * Returns the current number of used help actions.
     *
     * @return used help count
     */
    @Override
    public int getUsedHelps()
    {
        return usedHelps;
    }
}