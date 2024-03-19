package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.User;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.obstacles.SmokeScreen;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    private static File managerFile;

    public FileManager(String filePath) throws FileNotFoundException {
        managerFile = new File(filePath);
    }

    public static Level defaultLevelPvc() {
        Mapp map = defaultMapPvc();
        return new Level(map, "pvp");
    }

    public static Mapp defaultMapPvc() {
        Mapp map = new Mapp("earth");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160, 12, 900, 480));
        map.addFighter(new Ally(200, 600,3));
        map.addFighter(new Computer(1600, 600,1));
        return map;
    }
    public static Level defaultLevelPvp() {
        Mapp map = defaultMapPvp();
        return new Level(map, "pvp");
    }
    public static Mapp defaultMapPvp() {
        Mapp map = new Mapp("earth");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160, 12, 900, 480));
        map.addFighter(new Ally(200, 600,3));
        map.addFighter(new Ally(1600, 600,2));
        return map;
    }

    public static Level defaultLevelPve() {
        Mapp map = defaultMapPve();
        Level level = new Level(map, "pve");
        return level;
    }

    public static Mapp defaultMapPve() {
        Mapp map = new Mapp("space");
        map.addObstacle(new Wall(160, 12, 900, 440));
        map.addObstacle(new SpaceShip(-10,500,200));
        map.addObstacle(new SpaceShip(20,900,600));
        map.addObstacle(new SpaceShip(-30,1300,800));
        map.addObstacle(new SpaceShip(25,400,100));
        map.addFighter(new Ally(200, 600,1));
        return map;
    }

    public static void createPveLevel(String file) {
    }

    public static void createPvCLevel(String file) {
    }

    public static void saveUserdata(String username, String password) {
        try {
            FileWriter fileWriter = new FileWriter(managerFile, true); // true for append mode. (means you can add stuff to the text file without overwriting the current data)
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            //   bufferedWriter.write("User " );
            bufferedWriter.newLine();
            bufferedWriter.write("Username: " + username);
            bufferedWriter.newLine();
            bufferedWriter.write("Password: " + password);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static boolean loadUserData(String username, String Password) throws FileNotFoundException {
        boolean matchingUserName= false;
        boolean matchingPassWord= false;

        Scanner scanner = new Scanner(managerFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            try {
                if (line.startsWith("Username: ")) {
                    String storedUsername = line.substring("Username: ".length());
                    if (storedUsername.equals(username)) {
                        matchingUserName = true;
                    } else {
                        matchingUserName = false;
                    }
                }
                else if (line.startsWith("Password: ") && matchingUserName) { //Makes sure that the password matches with the correct username
                    String storedPassword = line.substring("Password: ".length());
                    if(storedPassword.equals(Password)){
                        matchingPassWord = true;
                        return true; //true means valid credentials for password and username
                    }

                }

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("out of bounds.");
                e.printStackTrace();
            }
        }
        scanner.close();
        return false; //false means username and password are incorrect or not submitted.
    }
}