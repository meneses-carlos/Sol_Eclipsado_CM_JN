package com.example.sol_eclipsado_cm_jn.model;

import java.util.List;
import java.util.Locale;

/**
 * Represents the main state of the Solar Eclipse game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.6.1
 * @since 1.0
 * @see ISolarEclipseGame
 */
public class SolarEclipseGame implements ISolarEclipseGame
{
    /**
     * Locale used to compare letters consistently.
     */
    private static final Locale SPANISH_LOCALE = new Locale("es", "ES");

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

    /**
     * Checks whether the entered letter matches the secret word
     * at the specified position.
     *
     * @param index position to validate
     * @param enteredLetter entered letter
     * @return true if the entered letter is correct for the position, false otherwise
     */
    @Override
    public boolean isLetterCorrectAt(int index, String enteredLetter)
    {
        if (enteredLetter == null || enteredLetter.isBlank())
        {
            return false;
        }

        if (index < 0 || index >= secretWord.length())
        {
            return false;
        }

        String expectedLetter = String.valueOf(secretWord.charAt(index));

        return normalizeComparableLetter(expectedLetter)
                .equals(normalizeComparableLetter(enteredLetter));
    }

    /**
     * Counts how many entered letters are correct in their respective positions.
     *
     * @param enteredLetters letters currently entered by the user
     * @return number of correct letters
     */
    @Override
    public int countCorrectLetters(List<String> enteredLetters)
    {
        if (enteredLetters == null)
        {
            return 0;
        }

        int correctCount = 0;

        for (int index = 0; index < enteredLetters.size() && index < secretWord.length(); index++)
        {
            if (isLetterCorrectAt(index, enteredLetters.get(index)))
            {
                correctCount++;
            }
        }

        return correctCount;
    }

    /**
     * Checks whether the whole secret word has been completed correctly.
     *
     * @param enteredLetters letters currently entered by the user
     * @return true if the whole word matches, false otherwise
     */
    @Override
    public boolean isSecretWordCompleted(List<String> enteredLetters)
    {
        if (enteredLetters == null || enteredLetters.size() != secretWord.length())
        {
            return false;
        }

        for (int index = 0; index < enteredLetters.size(); index++)
        {
            if (!isLetterCorrectAt(index, enteredLetters.get(index)))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Normalizes a comparable letter while preserving the distinction
     * between letters such as n/ñ and u/ü.
     * Only accented vowels are treated as equivalent to their non-accented forms.
     *
     * @param letter letter to normalize
     * @return normalized comparable letter
     */
    private String normalizeComparableLetter(String letter)
    {
        String upperLetter = letter.toUpperCase(SPANISH_LOCALE);

        return switch (upperLetter)
        {
            case "Á" -> "A";
            case "É" -> "E";
            case "Í" -> "I";
            case "Ó" -> "O";
            case "Ú" -> "U";
            default -> upperLetter;
        };
    }
}