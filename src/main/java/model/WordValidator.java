package com.example.sol_eclipsado_cm_jn.model;
/**
 * Basic validator for secret words used in the Solar Eclipse game.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 * @see IWordValidator
 */
public class WordValidator implements IWordValidator
{
    /**
     * Checks whether the provided word satisfies the game requirements.
     *
     * @param word candidate secret word
     * @return true if the word is valid, false otherwise
     */
    @Override
    public boolean isValidSecretWord(String word)
    {
        return false;
    }
}