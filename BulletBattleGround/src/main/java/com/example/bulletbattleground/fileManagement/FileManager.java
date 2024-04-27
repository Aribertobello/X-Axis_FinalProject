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
import java.util.HashMap;
import java.util.Scanner;

public class FileManager extends ClassSelectorController {

    private static File managerFile;

    static User user;

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
            if(line.startsWith("loot :")){
                scanLoot(line,level);
            }
        }
        level.setIndex(index);
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

//    public static void saveUserProgress() throws IOException {
//        File file = new File("nameOfYourFile");
//        FileOutputStream f = new FileOutputStream(file);
//        ObjectOutputStream s = new ObjectOutputStream(f);
//        s.writeObject(user.pVeUserProgress);
//        s.close();
//    }

//    public static void loadUserProgress() throws IOException, ClassNotFoundException {
//        File file = new File("temp");
//        FileInputStream f = new FileInputStream(file);
//        ObjectInputStream s = new ObjectInputStream(f);
//        HashMap<String, Object> fileObj2 = (HashMap<String, Object>) s.readObject(); //TODO fix
//        s.close();
//    }

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


    private static final String PROGRESS_FILE_PATH = "Files/txt/PVE.progress.txt";

    public static HashMap<String, Integer> loadPVEProgress() {
        HashMap<String, Integer> userPVEProgress = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROGRESS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    int progress = Integer.parseInt(parts[1].trim());
                    userPVEProgress.put(username, progress);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userPVEProgress;
    }

    public static void savePVEProgress(HashMap<String, Integer> userPVEProgress) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROGRESS_FILE_PATH))) {
            for (String username : userPVEProgress.keySet()) {
                int progress = userPVEProgress.get(username);
                writer.write(username + ": " + progress);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generates the default level for the player vs player mode
     * calls the defaultMapPvp to generate the map of the level
     *
     * @return applications default level for PVP
     */
    public static StandardLevel defaultLevelPvp() {
        Mapp map = defaultMapPvp();
        try {
            StandardLevel level = new StandardLevel(map, 3);
            level.addFighter(new Ally(1, 15, 200, 600), 1);
            level.addFighter(new Ally(2, 15, BattleGround.screenWidth - 200, 600), 2);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * generates the default map for player vs computer game mode
     * map is of type earth
     * map includes a 12x160 wall at 900,480
     * map has a player at 200,600 of type 3
     * map has a player at screenWidth - 200,600 of type 2
     *
     * @return applications default map for PVC
     */
    public static Mapp defaultMapPvp() {
        Mapp map = new Mapp("space");
        //map.addObstacle(new SmokeScreen(40,500,600));
        map.addObstacle(new Wall(160, 12, 900, 480, 300));
        return map;
    }

    public static Mapp defaultEduMap() {
        Mapp map = new Mapp("space");
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