package com.example.bulletbattleground.utility;

public interface BattleGroundObject {
    HitBox hitBox();

    /**
     * alligns the object in the scene according to position and velocity and other individual properties
     */
    void allign();
}
