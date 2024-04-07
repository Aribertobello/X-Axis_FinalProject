package com.example.bulletbattleground.game;

import com.example.bulletbattleground.gameObjects.projectiles.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Loadout {

    protected Projectile mainWeapon;

    protected String type;

    protected ArrayList<Grenade> grenades = new ArrayList<Grenade>();

    public Loadout(String type) {
        this.type = type;

        if (type.equalsIgnoreCase("light")) {
            mainWeapon = new Bullet();
            grenades.add(new Grenade());
            grenades.add(new Grenade());
            grenades.add(new SmokeGrenade());
        }

        if (type.equalsIgnoreCase("medium")) {
            mainWeapon = new Spear();
            grenades.add(new Grenade());
            grenades.add(new SmokeGrenade());
        }

        if (type.equalsIgnoreCase("heavy")) {
            mainWeapon = new Rocket();
            grenades.add(new Grenade());
            grenades.add(new SmokeGrenade());
            grenades.add(new SmokeGrenade());
        }

    }


}
