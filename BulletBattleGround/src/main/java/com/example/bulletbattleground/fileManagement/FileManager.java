package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.gameObjects.obstacles.SmokeScreen;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;

public class FileManager {


    public static Level defaultLevel(){
        Mapp map = defaultMap();
        Level level = new Level(map);

        return level;
    }
    public static Mapp defaultMap(){
        Mapp map = new Mapp();
        map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160,12,900,480));
        return map;
    }



}
