package com.example.bulletbattleground.utility;

public interface GameUI {
    /**
     *
     * @param dt increment of time the update go by
     * @param time the total time of the game
     * @return individual return for each child according to its palace in the workflow
     */
    public boolean[] update(double dt, double time);
}
