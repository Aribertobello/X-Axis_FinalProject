package com.example.bulletbattleground.game;

import com.example.bulletbattleground.utility.Bounds;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.MovingBody;
import com.example.bulletbattleground.utility.Vector;

public class Projectile extends MovingBody {
    protected Coordinate coordinate;
    protected int damage;
    protected Bounds bounds;
    protected Vector lift;
    protected void move(){}
    protected void release(){}

}
