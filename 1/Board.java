package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 15; // Assuming a 15x15 board, adjust as needed
    private static final Board instance = new Board(); // Singleton instance
    private Tile[][] tiles; // 2D array to hold tiles

    private Board() {
        // Initialize the board with null tiles
        tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (Tile[] row : tiles) {
            Arrays.fill(row, null);
        }
    }

    // Singleton pattern: return the single instance of the board
    public static Board getBoard() {
        return instance;
    }

    // Return a copy of the tiles array
    public Tile[][] getTiles() {
        Tile[][] copy = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, BOARD_SIZE);
        }
        return copy;
    }

    // Check if the word placement is legal on the board
    public boolean boardLegal(Word word) {
        // Implement the logic as described
        // Check if the word fits inside the board
        // Check if it rests on existing tiles
        // Check if it requires replacement of existing tiles
        // Return true if all conditions are met, false otherwise
        return false; // Placeholder, replace with actual logic
    }

    // Check if the word is legal according to the game dictionary
    public boolean dictionaryLegal(Word word) {
        // For now, always return true
        return true;
    }

    // Get an array of all new words that will be created by placing the given word
    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();
        // Implement logic to find new words created by placing the given word
        // Add them to the newWords list
        return newWords;
    }

    // Calculate the total score of the word, including bonuses
    public int getScore(Word word) {
        // Implement logic to calculate score based on word placement
        return 0; // Placeholder, replace with actual logic
    }
}
