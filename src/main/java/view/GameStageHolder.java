package com.example.sol_eclipsado_cm_jn.view;

import java.io.IOException;
/**
 * Provides a single shared instance of the game stage.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public class GameStageHolder
{
    private static GameStage instance;

    public static GameStage getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new GameStage();
        }

        return instance;
    }
}