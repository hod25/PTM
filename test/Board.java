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
        // Check if the word fits inside the board
        if (!isInsideBoard(word)) {
            return false;
        }
    
        // Check if it rests on existing tiles
        if (!restsOnExistingTiles(word)) {
            return false;
        }
    
        // Check if it requires replacement of existing tiles
        if (requiresReplacement(word)) {
            return false;
        }
    
        return true;
    }    

    // Check if the word is legal according to the game dictionary
    public boolean dictionaryLegal(Word word) {
        // For now, always return true-------- need to continue
        return true;
    }

    // Get an array of all new words that will be created by placing the given word
    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();
        // Implement logic to find new words created by placing the given word
        return newWords;
    }

    // Calculate the total score of the word, including bonuses
    public int getScore(Word word) {
        // Implement logic to calculate the score based on word placement
        return 0; // Placeholder, replace with actual logic
    }

    private boolean isInsideBoard(Word word) {
        int row = word.getRow();
        int col = word.getCol();
        boolean isHorizontal = word.isHorizontal();
        int length = word.getLength();
    
        // Check if the word is placed horizontally and fits within the board's columns
        if (isHorizontal && (col < 0 || col + length > BOARD_SIZE)) {
            return false;
        }
    
        // Check if the word is placed vertically and fits within the board's rows
        if (!isHorizontal && (row < 0 || row + length > BOARD_SIZE)) {
            return false;
        }
    
        return true;
    }
    
    

    private boolean restsOnExistingTiles(Word word) {
        // Implement logic to check if the word rests on existing tiles
        return false; // Placeholder, replace with actual logic
    }

    private boolean requiresReplacement(Word word) {
        // Implement logic to check if the word requires replacement of existing tiles
        return false; // Placeholder, replace with actual logic
    }
    public int tryPlaceWord(Word word) {
        if (!boardLegal(word)) {
            return -1; // Word placement is not legal
        }
    
        if (!dictionaryLegal(word)) {
            return -1; // Word is not legal according to the dictionary
        }
    
        // Calculate the score for the word placement
        int score = getScore(word);
    
        // Place the word on the board (implementation details depend on your game logic)
    
        return score;
    }
    
    
}
