package com.example.bulletbattleground.game;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.ClassSelectorController;
import com.example.bulletbattleground.gameObjects.projectiles.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;

import java.util.ArrayList;

public class Loadout {

    protected Projectile mainWeapon;
    protected int type;
    protected ArrayList<Grenade> grenades = new ArrayList<Grenade>();
    protected ArrayList<SmokeGrenade> smokeGrenades = new ArrayList<SmokeGrenade>();

    /**
     *
     * @param type
     */
    public Loadout(int type) {
        this.type = type;

        if (type==1) {
            mainWeapon = new Bullet();
            grenades.add(new Grenade());
            grenades.add(new Grenade());
            smokeGrenades.add(new SmokeGrenade());
        }

        if (type==2) {
            mainWeapon = new Spear();
            grenades.add(new Grenade());
            smokeGrenades.add(new SmokeGrenade());
        }

        if (type==3) {
            mainWeapon = new Rocket();
            grenades.add(new Grenade());
            smokeGrenades.add(new SmokeGrenade());
            smokeGrenades.add(new SmokeGrenade());
        }

    }

    /**
     *
     * @return
     */
    public HBox display() {
        Label typeLabel = new Label(type + " class   ");
        Label remaining = new Label("remaining Grenades:   " + grenades.size());
        HBox LoadoutBox = new HBox(10, typeLabel, remaining);

        for (Grenade grenade : grenades) {
            LoadoutBox.getChildren().add(new Label(grenade.toString()));
        }
        return LoadoutBox;
    }

}
