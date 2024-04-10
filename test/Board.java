package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class Board {
    private static Board instance ; // Singleton instance\
    private static final int BOARD_SIZE = 15; // Assuming a 15x15 board, adjust as needed
    private Tile[][] tiles; // 2D array to hold tiles
    boolean isFirstWord = true;
    boolean boardIsEmpty = true;
    // private Tile.Bag bag;
    // ArrayList<Word> words;
    // private static Board board = null;
    private final HashMap<Integer,List<String>> squaresMap;

    private Board() {
        // Initialize the board with null tiles
        tiles = new Tile[BOARD_SIZE][BOARD_SIZE]; // Board is empty at the start 
        squaresMap = new HashMap<>();
        for (int i=0; i<=4; i++){
            squaresMap.put(i, new ArrayList<>());
        }
        // for (Tile[] row : tiles) {
        //     Arrays.fill(row, null);
        // }
        // this.bag = Tile.Bag.getBag();
        // this.words = new ArrayList<>();
    }
    
    public static Board getBoard() {
        if (instance == null) {
            instance = new Board();
        }
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

    public boolean isInBound(Word word) {
        int row = word.getRow();
        int col = word.getCol();
        int len = word.getTiles().length;
        boolean isVertical = word.isVertical();
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            if (isVertical) {
                return row + len <= BOARD_SIZE;
            } else {
                return col + len <= BOARD_SIZE;
            }
        }
        return false;
    }
    // public boolean middle(Word word) {
    //     final int wordSize=word.tiles.length;
    //     if ((word.getRow()<0)||(word.getCol()<0)) {
    //         return false;
    //     }
    //     else if (word.isVertical()) {
    //         return ((word.getRow()<=7)&&(word.getRow()+wordSize>=7)) {
    //         }
    //     }
    //     else {
    //         return ((word.getCol()<=7)&&(word.getCol()+wordSize>=7)) {
    //         }
    //     }
    
    // }
    // Check if the word placement is legal on the board
    public boolean boardLegal(Word word) {
        int row = word.getRow();
        int col = word.getCol();  
        int len = word.getTiles().length;
        boolean isVertical = word.isVertical();
        if (isInBound(word)) {
            if (isVertical) {
                if (col == 7 && row <= 7 && row+len >= 7) {
                    return true;
                }
                
            }
            else if (row == 7 && col <= 7 && col+len >= 7) {
                return true;
                
            }
            else if (isInBound(word))
                if(isVertical)
                    for (int i = row; i < len; i++) {
                        if (tiles[row+i][col] != null) {
                            return true;
                        }
                    }
            else
                for (int i = col; i < len; i++) {
                    if (tiles[row][col+i] != null) {
                        return true;
                    }
                }
        }  
        // // Check if the word fits inside the board
        // if (!isInsideBoard(word)) {
        //     return false;
        // }    
        // // Check if it rests on existing tiles
        // if (!restsOnExistingTiles(word)) {//    error always returns false
        //     return false;
        // }
    
        // // // Check if it requires replacement of existing tiles
        // // if (requiresReplacement(word)) {
        // //     return false;
        // // }
        return false;
    }    

    // Check if the word is legal according to the game dictionary
    public boolean dictionaryLegal(Word word) {return true;}// For now, always return true

    // Get an array of all new words that will be created by placing the given word
    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();
        // Implement logic to find new words created by placing the given word
        return newWords;
    }

    // Calculate the total score of the word, including bonuses
    public int getScore(Word word) {
        // Implement logic to calculate the score based on word placement
        Tile[] tiles = word.getTiles();
        int score = 0;
    
        for (Tile tile : tiles) {
            char letter = tile.getLetter();
            // int letterScore = tile.getScore(letter); // Get score for the letter
            
            // Apply any bonus multiplier based on tile position
            int row = word.getRow();
            int col = word.getCol();
            int multiplier = getTileMultiplier(row, col);
    
            // Add letter score multiplied by bonus to total score
            // score += letterScore * multiplier;
        }
    
        return score;
    
        // return 0; // Placeholder, replace with actual logic
        
    }
    
        // Method to get the bonus multiplier for each tile position
    private int getTileMultiplier(int row, int col) {
        // Implement logic to return bonus multiplier based on tile position
        if ((row == 1 && col == 1) || (row == 15 && col == 1) || (row == 8 && (col == 1 || col == 15))
                || (col == 8 && (row == 1 || row == 15)) || (row == 15 && col == 15) || (row == 1 && col == 8)) {
            return 3; // Triple Word
        } else if ((row == 2 && col == 2) || (row == 3 && col == 3) || (row == 4 && col == 4) || (row == 5 && col == 5)
                || (row == 2 && col == 14) || (row == 3 && col == 13) || (row == 4 && col == 12) || (row == 5 && col == 11)
                || (row == 14 && col == 2) || (row == 13 && col == 3) || (row == 12 && col == 4) || (row == 11 && col == 5)
                || (row == 11 && col == 11) || (row == 12 && col == 12) || (row == 13 && col == 13) || (row == 14 && col == 14)
                || (row == 8 && col == 8)) {
            return 2; // Double Word
        } else if ((row == 2 && (col == 6 || col == 10)) || (row == 6 && (col == 2 || col == 6 || col == 10 || col == 14))
                || (row == 10 && (col == 2 || col == 6 || col == 10 || col == 14)) || (row == 14 && (col == 6 || col == 10))) {
            return 3; // Triple Letter
        } else if ((row == 4 && (col == 1 || col == 15)) || (col == 4 && (row == 1 || row == 15))
                || (row == 1 && col == 12) || (row == 3 && (col == 7 || col == 9)) || (row == 7 && (col == 3 || col == 7 || col == 9 || col == 13))
                || (row == 8 && (col == 4 || col == 14)) || (row == 9 && (col == 3 || col == 7 || col == 9 || col == 13)) || (row == 12 && (col == 1 || col == 8 || col == 15))
                || (row == 13 && (col == 7 || col == 9)) || (row == 15 && (col == 4 || col == 12))) {
            return 2; // Double Letter
        } else {
            return 1; // No Bonus
        }
    }

    // private boolean isInsideBoard(Word word) {
    //     int row = word.getRow();
    //     int col = word.getCol();
    //     boolean isHorizontal = word.isHorizontal();
    //     int length = word.getLength();
    
    //     // Check if the word is placed horizontally and fits within the board's columns
    //     if (isHorizontal && (col < 0 || col + length > BOARD_SIZE)) {
    //         return false;
    //     }
    
    //     // Check if the word is placed vertically and fits within the board's rows
    //     if (!isHorizontal && (row < 0 || row + length > BOARD_SIZE)) {
    //         return false;
    //     }
    
    //     return true;
    // }
    // private boolean isInsideBoard(Word word) {
    //     // Implement logic to check if the word fits inside the board
    //     if (word.getRow() >= 0 && word.getRow() < BOARD_SIZE && word.getCol() >= 0 && word.getCol() < BOARD_SIZE){
    //         return true;
    //     }
    //     else
    //         return false;
    // }
    
    // private boolean restsOnExistingTiles(Word word) {
    //     // Tile[][] currentTiles = getTiles(); // Get the current state of the board
        
    //     // // boolean isFirstWord = true; // Assume it's the first word unless proven otherwise
    //     // for (Tile[] row : currentTiles) {
    //     //     for (Tile tile : row) {
    //     //         if (tile != null) {
    //     //             isFirstWord = false; // If there's at least one non-null tile, it's not the first word
    //     //             // break;
    //     //         }
    //     //     }
    //     //     if (!isFirstWord) {
    //     //         break;
    //     //     }
    //     // }
        
    //     // if (isFirstWord) {
    //     //     placeWord(word);
    //         // Check if the word passes through the center tile (8,8)
    //         // isFirstWord = false; // after first word is placed, it is no longer the first word
    //         // return true; // The first word passes through the center tile
    //     //     int row = word.getRow();
    //     //     int col = word.getCol();
    //     //    if (row == 7 && col == 7) {
    //     //         return true; // The first word passes through the center tile
    //     //     } else {
    //     //         return false; // The first word does not pass through the center tile
    //     //     }
    //     return true;
    //     // }
    //     //      else
    //     // {
    //     //     // Check if the word rests on existing tiles // Error the board is always empty
    //     //     boolean restsOnExisting = false;
    //     //     Tile[] tiles = word.getTiles();
    //     //     for (Tile tile : word.tiles) {
    //     //         int row = word.getRow();
    //     //         int col = word.getCol();
    //     //         if (currentTiles[row][col] != null && this.getTiles()[row][col].getLetter() != tile.getLetter()) {
    //     //             restsOnExisting = true;
    //     //             currentTiles[row][col] = null;
    //     //             break;
    //     //         }
    //     //     }
    //     //     return restsOnExisting;
    //     // }
    // }
    

    // private boolean requiresReplacement(Word word) {
    //     Tile[][] currentTiles = getTiles(); // Get the current state of the board

    //     // Check if any tile of the word overlaps with an existing tile
    //     Tile[] tiles = word.getTiles();
    //     for (Tile tile : tiles) {
    //         int row = tile.getRow();
    //         int col = tile.getCol();
    //         if (currentTiles[row][col] != null) {
    //             return true; // If the tile overlaps with an existing tile, replacement is required
    //         }
    //     }
    //     return false; // If no overlap is found, no replacement is required
    // }

    public int tryPlaceWord(Word word) {
        // if (!boardLegal(word)) 
        //     boardIsEmpty = false;
        //     if (word.isVertical()){ 
        //     int j=word.getCol();
        //     for (int i=word.getRow(); i<word.getTiles().length+word.tiles.length; i++){
        //         if (tiles[i][j]==null){ 
        //             tiles[i][j]=word.tiles[i-word.getTiles().length];
        //         }
        //     }
        //     }
        //     else{
        //         int i=word.getRow();
        //         for (int j=word.getCol(); j<word.getCol()+word.tiles.length; j++){
        //             if (tiles[i][j]==null){
        //                 tiles[i][j]=word.tiles[j-word.getTiles().length];
        //             }
        //         }
                
        //     }
            return -1; // Word placement is not legal
        }
    // }
        // if (!dictionaryLegal(word)) {
        //     return -1; // Word is not legal according to the dictionary
        // }
    
        // // Calculate the score for the word placement
        // int score = getScore(word);
        // System.out.println(score);
        // // Place the word on the board (implementation details depend on your game logic)
        // placeWord(word);
        // return score;
    // }
    
    // Method to place the word on the board
    // private boolean placeWord(Word word) {
    //     Tile[][] currentTiles = getTiles(); // Get the current state of the board
    //     Tile[] wordTiles = word.getTiles(); // Get the tiles of the word
    //     int row = word.getRow(); // Get the starting row of the word
    //     int col = word.getCol(); // Get the starting column of the word
    //     boolean isVertical = word.isVertical(); // Check if the word is vertical

    //     for (Tile tile : wordTiles) {
    //         // Place the tile on the board
    //         currentTiles[row][col] = tile;

    //         // Move to the next position based on whether the word is vertical or horizontal
    //         if (isVertical) {
    //             row++;
    //         } else {
    //             col++;
    //         }
    //     }

    //     // Update the board with the placed word
    //     this.tiles = currentTiles;
    //     return true;
    // }
}