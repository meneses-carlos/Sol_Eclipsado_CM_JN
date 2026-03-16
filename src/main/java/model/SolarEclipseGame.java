package com.example.sol_eclipsado_cm_jn.model;
/**
 * Represents the main game state for the Solar Eclipse project.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 * @see ISolarEclipseGame
 */
public class SolarEclipseGame implements ISolarEclipseGame
{
    private String secretWord;
    private int errors;
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

    @Override
    public void setSecretWord(String secretWord)
    {
        this.secretWord = secretWord;
    }

    @Override
    public String getSecretWord()
    {
        return secretWord;
    }

    @Override
    public int getErrors()
    {
        return errors;
    }

    @Override
    public int getUsedHelps()
    {
        return usedHelps;
    }
}