package com.example.sol_eclipsado_cm_jn.view;

import java.io.IOException;
/**
 * Provides a single shared instance of the welcome stage.
 *
 * @author Jorge Navia
 * @author Carlos Meneses
 * @version 1.0
 * @since 1.0
 */
public class WelcomeStageHolder
{

    private static WelcomeStage instance;
    /**
     * Returns the shared welcome stage instance.
     *
     * @return singleton welcome stage instance
     * @throws IOException if the stage cannot be created
     */
    public static WelcomeStage getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new WelcomeStage();
        }

        return instance;
    }
}