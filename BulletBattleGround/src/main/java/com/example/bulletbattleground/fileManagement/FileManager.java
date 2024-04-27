package com.example.bulletbattleground.fileManagement;

import com.example.bulletbattleground.BattleGround;
import com.example.bulletbattleground.game.Level;
import com.example.bulletbattleground.controllers.ClassSelectorController;
import com.example.bulletbattleground.game.levels.StandardLevel;
import com.example.bulletbattleground.game.Mapp;
import com.example.bulletbattleground.game.levels.EduLevel;
import com.example.bulletbattleground.game.levels.FreePlayLevel;
import com.example.bulletbattleground.gameObjects.Loot.Loot;
import com.example.bulletbattleground.gameObjects.fighters.Ally;
import com.example.bulletbattleground.gameObjects.fighters.Computer;
import com.example.bulletbattleground.gameObjects.obstacles.SpaceShip;
import com.example.bulletbattleground.gameObjects.obstacles.Wall;
import com.example.bulletbattleground.utility.Coordinate;
import com.example.bulletbattleground.utility.Vector;
import javafx.stage.Screen;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class FileManager extends ClassSelectorController {

    //TODO GameRules
    private static File managerFile;

    public static int loadoutType;


    /**
     * Constructs a new FileManager instance with the specified file path.
     *
     * @param filePath The path to the file managed by this FileManager.
     * @throws FileNotFoundException If the file specified by the filePath does not exist.
     */
    public FileManager(String filePath) throws FileNotFoundException {
        managerFile = new File(filePath);
    }

    public static Level createLevel(String filePath) throws ParseException, IOException {
        Level level = null;
        String mapLocation = "";
        String description = "";
        int type = 0;
        int index = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("index :")) index = Integer.valueOf(line.substring(8).trim());
            if (line.startsWith("type :")) type = Integer.valueOf(line.substring(7).trim());
            if (line.startsWith("map :")) mapLocation = line.substring(6).trim();
            if (line.startsWith("fighters :")) {
                switch (type) {
                    case 1,2,3 -> level = new StandardLevel(createMap(mapLocation), type);
                    case 0 -> level = new FreePlayLevel(createMap(mapLocation));
                    default -> level = new EduLevel(createMap(mapLocation));
                }
                readFighters(scanner, level);
            }
            if(line.startsWith("loot :")) scanLoot(line,level);
            if(line.startsWith("Description :")) {
                scanner.nextLine();
                String l = scanner.nextLine();
                while(!l.endsWith("}")) {
                    description = description + "\n" + l ;
                    l = scanner.nextLine();
                }
            }
        }
        level.setIndex(index);
        level.getDescriptionLabel().setText(description);
        level.setDescription(description);
        return level;
    }

    public static Mapp createMap(String filePath) throws ParseException {
        Mapp map = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("type : ")) map = new Mapp(line.substring(7, 12));
            if (line.startsWith("Forces :"))  readForces(scanner,map);
            if (line.startsWith("Obstacles :")) {
                readObsctles(scanner, map);
            }
        }
        return map;
    }

    private static void scanLoot(String line, Level level) throws ParseException {
        Coordinate coordinate = new Coordinate(0, 0);
            Scanner lineScanner = new Scanner(line);
            String str = "";
            while (!str.startsWith("}")) {
                str = lineScanner.next();
                if (str.equalsIgnoreCase("coordinate")) {
                    lineScanner.skip("...");
                    coordinate = Coordinate.valueOf(lineScanner.next());
                    ((StandardLevel)level).addLoot(new Loot((int)coordinate.getX(),(int)coordinate.getY()));
                }
            }
    }

    private static void readFighters(Scanner scanner, Level level) throws ParseException {

        int loadoutType = 0;
        int health = 0;
        Coordinate coordinate = new Coordinate(0, 0);
        String line = scanner.nextLine();
        while (!line.startsWith("}")) {
            Scanner lineScanner = new Scanner(line);
            if (line.startsWith("ally") || line.startsWith("computer")) {
                while (!lineScanner.next().startsWith("}")) {
                    String str = lineScanner.next();
                    if (str.equalsIgnoreCase("loadout")) {
                        lineScanner.skip("...");
                        loadoutType = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("health")) {
                        lineScanner.skip("...");
                        health = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("coordinate")) {
                        lineScanner.skip("...");
                        coordinate = Coordinate.valueOf(lineScanner.next());
                    }
                }
                if (line.startsWith("ally"))
                    level.addFighter(new Ally(loadoutType, health, (int) coordinate.getX(), (int) coordinate.getY()), 1);
                if (line.startsWith("computer"))
                    level.addFighter(new Computer(loadoutType, health, (int) coordinate.getX(), (int) coordinate.getY()), 2);
            }
            line = scanner.nextLine();
        }
    }

    private static void readForces(Scanner scanner, Mapp map) throws ParseException {
        double gMag = 0;
        double aiResMag = 0;
        Vector wind = new Vector(0, 0);
        String str = scanner.next();
        while (!str.startsWith("}")) {
            if(str.startsWith("GMagnitude")){
                scanner.skip("...");
                gMag = scanner.nextDouble();
            }
            if(str.startsWith("AirResMagnitude")){
                scanner.skip("...");
                aiResMag = scanner.nextDouble();
            }
            if(str.startsWith("wind")){
                scanner.skip("...");
                wind = Vector.valueOf(scanner.next());
            }
            str = scanner.next();
        }
        map.setGravityMagnitude(gMag);
        map.setAirResistanceMagnitude(aiResMag);
        map.environmentForces[2] = wind;
    }

    private static void readObsctles(Scanner scanner, Mapp map) throws ParseException {
        int height = 0;
        int width = 0;
        Coordinate coordinate = new Coordinate(0, 0);
        int rotation = 0;
        int mass = 0;
        int speed = 0;
        String line = scanner.nextLine();
        while (!line.startsWith("}")) {
            Scanner lineScanner = new Scanner(line);
            if (line.startsWith("wall")) {
                while (!lineScanner.next().startsWith("}")) {
                    String str = lineScanner.next();
                    if (str.equalsIgnoreCase("height")) {
                        lineScanner.skip("...");
                        height = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("width")) {
                        lineScanner.skip("...");
                        width = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("coordinate")) {
                        lineScanner.skip("...");
                        coordinate = Coordinate.valueOf(lineScanner.next());
                    }
                    if (str.equalsIgnoreCase("rotation")) {
                        lineScanner.skip("...");
                        rotation = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("mass")) {
                        lineScanner.skip("...");
                        mass = lineScanner.nextInt();
                    }
                }
                map.addObstacle(new Wall(height, width, (int) coordinate.getX(), (int) coordinate.getY(), mass));
            }
            if (line.startsWith("spaceship")) {
                while (!lineScanner.next().startsWith("}")) {
                    String str = lineScanner.next();
                    if (str.equalsIgnoreCase("speed")) {
                        lineScanner.skip("...");
                        speed = lineScanner.nextInt();
                    }
                    if (str.equalsIgnoreCase("coordinate")) {
                        lineScanner.skip("...");
                        coordinate = Coordinate.valueOf(lineScanner.next());
                    }
                }
                map.addObstacle(new SpaceShip(speed, (int) coordinate.getX(), (int) coordinate.getY()));
            }
            line = scanner.nextLine();
        }
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

    public static Mapp defaultEduMap() {
        Mapp map = new Mapp("earth");
        return map;
    }

    public static EduLevel defaultEduLevel() {
        Mapp map = defaultEduMap();
        try {
            EduLevel level = new EduLevel(map);
            level.addFighter(new Ally(2, 15, 200, 600), 1);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FreePlayLevel freePlayLevel() {
        Mapp map = new Mapp("space");
        try {
            return new FreePlayLevel(map);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}