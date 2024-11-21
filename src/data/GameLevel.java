package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameLevel {
    public String playerID;
    public int levelUnlocked = 0;
    String[] timings = new String[PlayerData.NUMBER_OF_GAME_LEVELS];

    public final static String FILE_PATH = "level.txt";

    public GameLevel(PlayerData playerData) {
        this.playerID = playerData.playerID;
        this.levelUnlocked = playerData.gameLevel;
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(this.playerID + "," + this.levelUnlocked);
            for (String timing : timings) {
                writer.write("," + (timing != null ? timing : ""));
            }
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while saving game level data to file: " + e.getMessage());
        }
    }

    public void loadLevels(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[0].equals(this.playerID)) {
                    this.playerID = parts[0];
                    this.levelUnlocked = Integer.parseInt(parts[1]);

                    if (parts.length > 2) {
                        for (int i = 2; i < parts.length; i++) {
                            timings[i - 2] = parts[i];
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading game level data from file: " + e.getMessage());
        }
    }

    public static void deletePlayerById(String playerID, String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(playerID + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void updateUnlockedLevel(String playerID, int newLevel, String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(playerID + ",")) {
                    String[] parts = line.split(",");
                    parts[1] = String.valueOf(newLevel);
                    StringBuilder updatedLine = new StringBuilder(parts[0] + "," + parts[1]);
                    for (int i = 2; i < parts.length; i++) {
                        updatedLine.append(",").append(parts[i]);
                    }
                    lines.add(updatedLine.toString());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void updateTimings(String playerID, int level, String timing, String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(playerID + ",")) {
                    String[] parts = line.split(",");
                    if (level + 2 < parts.length) {
                        parts[level + 2] = timing;
                    } else {
                        System.err.println("Invalid level or file data is corrupted.");
                    }
                    StringBuilder updatedLine = new StringBuilder(parts[0] + "," + parts[1]);
                    for (int i = 2; i < parts.length; i++) {
                        updatedLine.append(",").append(parts[i]);
                    }
                    lines.add(updatedLine.toString());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static String getTimingForLevel(String playerID, int level, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(playerID + ",")) {
                    String[] parts = line.split(",");
                    if (level + 2 < parts.length) {
                        return parts[level + 2]; // Return the timing for the specified level
                    } else {
                        System.err.println("Invalid level or file data is corrupted.");
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return null; // Return null if no matching player or level is found
    }

}
