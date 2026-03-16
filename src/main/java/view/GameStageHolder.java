package com.example.sol_eclipsado_cm_jn.view;

import java.io.IOException;

/**
 * Provides a single shared instance of the game stage.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.3
 * @since 1.0
 */
public class GameStageHolder
{
    /**
     * Shared instance of the game stage.
     */
    private static GameStage instance;

    /**
     * Returns the shared game stage instance.
     *
     * @return singleton game stage instance
     * @throws IOException if the stage cannot be created
     */
    public static GameStage getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new GameStage();
        }

        return instance;
    }
}