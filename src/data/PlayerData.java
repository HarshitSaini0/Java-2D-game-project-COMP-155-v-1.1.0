package data;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlayerData {
    public String playerID;
    public String name;
    public int age;

    public int gameLevel;
    public LocalDate createdAt;

    public final static int NUMBER_OF_GAME_LEVELS = 10;
    public final static String FILE_PATH = "player.txt";

    public PlayerData(String playerID) {                            // Copy constructor
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean playerFound = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(playerID)) {
                    this.playerID = parts[0];
                    this.name = parts[1];
                    this.age = Integer.parseInt(parts[2]);
                    this.gameLevel = Integer.parseInt(parts[3]);
                    this.createdAt = LocalDate.parse(parts[4]);
                    playerFound = true;
                    break;
                }
            }
            if (!playerFound) {
                throw new IllegalArgumentException("Player ID not found in the file.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading player data from file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while parsing player data: " + e.getMessage());
        }
    }


    public PlayerData(String name, int age) {
        this.name = name;
        this.age = age;
        this.playerID = "Player" + System.currentTimeMillis();

        this.gameLevel = 0;
        this.createdAt = LocalDate.now();

    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(
                    this.playerID
                        +","+this.name
                        +","+this.age
                        +","+this.gameLevel
                        +","+this.createdAt
            );
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while saving player data to file: " + e.getMessage());
        }

    }
    public ArrayList<String> getPlayerData(String fileName, String playerID){

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equals(playerID)){
                    ArrayList <String> thePlayer = new ArrayList<>();
                    for (String part : parts) {
                        thePlayer.set(1, part);
                    }
                    return thePlayer;
                }

            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading player data from file: " + e.getMessage());
        }
        return null;
    }

    static  public void deletePlayer(String fileName, String playerID) {
        ArrayList<String> lines = new ArrayList<>();

        // Step 1: Read the file and identify the line to delete
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(playerID)) {  // Keep the line if it doesn't match the playerID
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading player data from file: " + e.getMessage());
            return;
        }

        // Step 2: Write the remaining lines back to the file (overwriting the file)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();  // Add a new line after each entry
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing player data to file: " + e.getMessage());
        }
    }


    public void updateGameLevel(){
        if(this.gameLevel< NUMBER_OF_GAME_LEVELS){
            this.gameLevel++;
        }
        this.saveToFile(FILE_PATH);
    }
}
