package com.example.sol_eclipsado_cm_jn.model;
/**
 * Defines the contract for validating secret words in the game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public interface IWordValidator
{
    /**
     * Validates whether a given word can be used as a secret word.
     *
     * @param word word to validate
     * @return true if the word is valid, false otherwise
     */
    boolean isValidSecretWord(String word);
}