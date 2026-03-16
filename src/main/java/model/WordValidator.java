package com.example.sol_eclipsado_cm_jn.model;

/**
 * Validates the secret word according to the game requirements.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.2
 * @since 1.0
 * @see IWordValidator
 */
public class WordValidator implements IWordValidator
{
    /**
     * Minimum number of allowed letters for the secret word.
     */
    private static final int MIN_LENGTH = 6;

    /**
     * Maximum number of allowed letters for the secret word.
     */
    private static final int MAX_LENGTH = 12;

    /**
     * Regular expression that allows only Spanish alphabet letters,
     * including accented vowels, ñ, Ñ, ü and Ü.
     */
    private static final String SPANISH_WORD_REGEX = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]+$";

    /**
     * Checks whether a word is valid to be used as the secret word.
     *
     * @param word candidate word to validate
     * @return true if the word is valid, false otherwise
     */
    @Override
    public boolean isValidSecretWord(String word)
    {
        return getValidationErrorMessage(word).isEmpty();
    }

    /**
     * Returns the validation error message for a given word.
     * If the word is valid, this method returns an empty string.
     *
     * @param word candidate word to validate
     * @return validation error message or an empty string if valid
     */
    @Override
    public String getValidationErrorMessage(String word)
    {
        if (word == null || word.isBlank())
        {
            return "Debes escribir una palabra secreta.";
        }

        if (!word.equals(word.trim()))
        {
            return "La palabra no puede tener espacios al inicio ni al final.";
        }

        if (word.matches(".*\\s+.*"))
        {
            return "La palabra debe ser una sola, sin espacios intermedios.";
        }

        if (word.length() < MIN_LENGTH || word.length() > MAX_LENGTH)
        {
            return "La palabra debe tener entre 6 y 12 letras.";
        }

        if (!word.matches(SPANISH_WORD_REGEX))
        {
            return "Solo se permiten letras del alfabeto español, incluyendo ñ y vocales con acento.";
        }

        return "";
    }
}