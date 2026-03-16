package com.example.sol_eclipsado_cm_jn.model;

/**
 * Defines the contract for validating secret words in the Solar Eclipse game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.2
 * @since 1.0
 */
public interface IWordValidator
{
    /**
     * Checks whether a word is valid to be used as the secret word.
     *
     * @param word candidate word to validate
     * @return true if the word is valid, false otherwise
     */
    boolean isValidSecretWord(String word);

    /**
     * Returns the validation error message for a given word.
     * If the word is valid, this method returns an empty string.
     *
     * @param word candidate word to validate
     * @return validation error message or an empty string if valid
     */
    String getValidationErrorMessage(String word);
}