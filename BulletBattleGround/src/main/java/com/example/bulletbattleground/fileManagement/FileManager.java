package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.obstacles.SmokeScreen;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;

public class FileManager {


    public static Level defaultLevelPvp(){
        Mapp map = defaultMapPvp();
        Level level = new Level(map,"pvp");
        return level;
    }
    public static Mapp defaultMapPvp(){
        Mapp map = new Mapp("earth");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160,12,900,480));
        map.addFighter(new Ally(200,600));
        map.addFighter(new Computer(1600,600));
        return map;
    }
    public static Level defaultLevelPve(){
        Mapp map = defaultMapPve();
        Level level = new Level(map,"pve");
        return level;
    }
    public static Mapp defaultMapPve(){
        Mapp map = new Mapp("space");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160,12,900,440));
        map.addFighter(new Ally(200,600));
        return map;
    }

    public static void createPveLevel(String file){}
    public static void createPvCLevel(String file){}
    public static void saveUserdata(String file){}

}
