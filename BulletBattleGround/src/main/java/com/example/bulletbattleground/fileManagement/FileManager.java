package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.controllers.ClassSelectorController;
import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.levels.EduLevel;
import com.example.bulletbattleground.game.levels.FreePlayLevel;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import javafx.stage.Screen;

import java.io.*;
import java.util.Scanner;

public class FileManager extends ClassSelectorController {

    private static File managerFile;

    public static int loadoutType;

    static int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

    /**
     * Constructs a new FileManager instance with the specified file path.
     *
     * @param filePath The path to the file managed by this FileManager.
     * @throws FileNotFoundException If the file specified by the filePath does not exist.
     */
    public FileManager(String filePath) throws FileNotFoundException {
        managerFile = new File(filePath);
    }

    /**
     *Generates the default level for the player vs computer mode
     * calls the defaultMapPvc to generate the map of the level
     * @return applications default level for PVC
     */
    public static StandardLevel defaultLevelPvc() {
        Mapp map = defaultMapPvc();
        try {
            StandardLevel level = new StandardLevel(map, 2);
            level.addFighter(new Ally(200, 600, loadoutType),1);
            level.addFighter(new Computer(screenWidth - 200, 600, 1),2);
            return level;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *generates the default map for player vs computer game mode
     * map is of type earth
     * map includes a 12x160 wall at 900,480
     * map has a player at 200,600 of type 3
     * map has an opponent at screenWidth - 200,600 of type
     * @return applications default map for PVC
     */
    public static Mapp defaultMapPvc() {
        Mapp map = new Mapp("earth");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160, 12, 900, 480,90));
        return map;
    }

    /**
     *Generates the default level for the player vs player mode
     * calls the defaultMapPvp to generate the map of the level
     * @return applications default level for PVP
     */
    public static StandardLevel defaultLevelPvp() {
        Mapp map = defaultMapPvp();
        try {
            StandardLevel level = new StandardLevel(map, 3);
            level.addFighter(new Ally(200, 600, loadoutType),1);
            level.addFighter(new Ally(screenWidth - 200, 600, 2),2);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *generates the default map for player vs computer game mode
     * map is of type earth
     * map includes a 12x160 wall at 900,480
     * map has a player at 200,600 of type 3
     * map has a player at screenWidth - 200,600 of type 2
     * @return applications default map for PVC
     */
    public static Mapp defaultMapPvp() {
        Mapp map = new Mapp("space");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160, 12, 900, 480,300));
        return map;
    }

    /**
     *Generates the default level for the player vs environment mode
     * calls the defaultMapPve to generate the map of the level
     * @return applications default level for PVE
     */
    public static StandardLevel defaultLevelPve() {
        Mapp map = defaultMapPve();
        try {
            StandardLevel level = new StandardLevel(map, 1);
            level.addFighter(new Ally(200, 600, loadoutType),1);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *generates the default map for player vs environnement game mode
     * map is of type space
     * map includes 4 moving spaceships
     * map has a player at 200,600 of type 1
     * @return applications default map for PVE
     */
    public static Mapp defaultMapPve() {
        Mapp map = new Mapp("space");
        //map.addObstacle(new SpaceShip(0, 200, 500));
        map.addObstacle(new SpaceShip(20, 900, 600));
        map.addObstacle(new SpaceShip(-30, 1300, 800));
        map.addObstacle(new SpaceShip(25, 400, 100));
        map.addObstacle(new SpaceShip(-5, 200, 500));
        return map;
    }
    public static Mapp defaultEduMap() {
        Mapp map = new Mapp("space");
        //map.addObstacle(new SmokeScreen(40,500,600));
        return map;
    }
    public static EduLevel defaultEduLevel() {
        Mapp map = defaultEduMap();
        try {
            EduLevel level = new EduLevel(map);
            level.addFighter(new Ally(200, 600, 2),1);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FreePlayLevel freePlayLevel(){
        Mapp map = new Mapp("space");
        try {
            return new FreePlayLevel(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createPveLevel(String file) { //TODO
    }

    public static void createPvCLevel(String file) { //TODO
    }

    /**
     * Saves user data (username and password) to the file managed by this FileManager.
     *
     * @param username The username to be saved.
     * @param password The password associated with the username.
     */
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

    /**
     * Loads user data from the file managed by this FileManager and checks if the provided username and password match the stored credentials from the player.
     *
     * @param username The username to be verified.
     * @param Password The password to be verified with the correct username.
     * @return true if the provided username and password match the player's stored credentials, false otherwise.
     * @throws FileNotFoundException If the file managed by this FileManager is not found.
     */
    public static boolean loadUserData(String username, String Password) throws FileNotFoundException {
        boolean matchingUserName = false;
        boolean matchingPassWord = false;

        Scanner scanner = new Scanner(managerFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            try {
                if (line.startsWith("Username: ")) {
                    String storedUsername = line.substring("Username: ".length());
                    matchingUserName = storedUsername.equals(username);
                } else if (line.startsWith("Password: ") && matchingUserName) { //Makes sure that the password matches with the correct username
                    String storedPassword = line.substring("Password: ".length());
                    if (storedPassword.equals(Password)) {
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