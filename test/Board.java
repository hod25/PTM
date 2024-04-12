package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    private static HashMap<String, String> boardScores;

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
        newWords.add(word);
        // Implement logic to find new words created by placing the given word
        return newWords;
    }

    // Calculate the total score of the word, including bonuses
    public int getScore(Word word) {
        // Implement logic to calculate the score based on word placement
        Tile[] tiles = word.getTiles();
        int tileScore = 0;
        int scoreSum = 0;
        int multiplyWord = 1;
        //int score = 0;


        for (int i=0; i<word.getTiles().length; i++) {
            int row = word.isVertical() ? word.getRow() + i : word.getRow(); 
            int col = word.isVertical() ? word.getCol() : word.getCol() + i; 
            if (tiles[i]==null) {
                    Board board = getBoard();
                    if (board != null && board.tiles[row][col] != null) {
                        tileScore = board.tiles[row][col].score;
                    } else {
                        // Handle the case where the board or tile is null
                    }                    scoreSum += tileScore;
                    continue;
                }    
            else{
                tileScore = tiles[i].score;
            }
            switch (getTileMultiplier(row, col)) {
                case 0:
                    scoreSum += tileScore;
                    break;
                case 1:// Double Letter
                    scoreSum += tileScore * 3;
                    break;
                case 2:// Double Word
                    scoreSum += tileScore;
                    multiplyWord *= 2;
                    break;
                case 3:// Triple Word
                    scoreSum += tileScore;
                    multiplyWord *= 3;
                    break;
                case 4:// Double Letter
                    scoreSum += tileScore * 2;
                    break;
                default:
                    break;
            }
            System.out.println(scoreSum);
        }
    
        return scoreSum*multiplyWord;
    
        // return 0; // Placeholder, replace with actual logic
        
    }
    
        // Method to get the bonus multiplier for each tile position
    private int getTileMultiplier(int row, int col) {
        if ((row == 0 && col == 0) || (row == 14 && col == 0) || (row == 7 && (col == 0 || col == 14))
        || (col == 7 && (row == 0 || row == 14)) || (row == 14 && col == 14) || (row == 0 && col == 7)) {
        return 3; // Triple Word
        } else if ((row == 1 && col == 1) || (row == 2 && col == 2) || (row == 3 && col == 3) || (row == 4 && col == 4)
        || (row == 1 && col == 13) || (row == 2 && col == 12) || (row == 3 && col == 11) || (row == 4 && col == 10)
        || (row == 13 && col == 1) || (row == 12 && col == 2) || (row == 11 && col == 3) || (row == 10 && col == 4)
        || (row == 10 && col == 10) || (row == 11 && col == 11) || (row == 12 && col == 12) || (row == 13 && col == 13)
        || (row == 7 && col == 7)) {
        return 2; // Double Word
        } else if ((row == 1 && (col == 5 || col == 9)) || (row == 5 && (col == 1 || col == 5 || col == 9 || col == 13))
        || (row == 9 && (col == 1 || col == 5 || col == 9 || col == 13)) || (row == 13 && (col == 5 || col == 9))) {
        return 1; // Triple Letter
        } else if ((row == 3 && (col == 0 || col == 14)) || (col == 3 && (row == 0 || row == 14))
        || (row == 0 && col == 11) || (row == 2 && (col == 6 || col == 8)) || (row == 6 && (col == 2 || col == 6 || col == 8 || col == 12))
        || (row == 7 && (col == 3 || col == 12)) || (row == 8 && (col == 2 || col == 6 || col == 8 || col == 12))
        || (row == 11 && (col == 0 || col == 7 || col == 14)) || (row == 12 && (col == 6 || col == 8)) || (row == 14 && (col == 3 || col == 11))) {
        return 4; // Double Letter    
        } else {
            return 0; // No Bonus
        }
    }
    public int tryPlaceWord(Word word) {
        if (!boardLegal(word)) 
            boardIsEmpty = false;
            if (word.isVertical()){ 
            int j=word.getCol();
            for (int i=word.getRow(); i<word.getTiles().length+word.tiles.length; i++){
                if (tiles[i][j]==null){ 
                    tiles[i][j]=word.tiles[i-word.getTiles().length];
                }
            }
            }
            else{
                int i=word.getRow();
                for (int j=word.getCol(); j<word.getCol()+word.tiles.length; j++){
                    if (tiles[i][j]==null){
                        tiles[i][j]=word.tiles[j-word.getCol()];
                    }
                }
                
            }
            // return -1; // Word placement is not legal
        if (!dictionaryLegal(word)) {
            return -1; // Word is not legal according to the dictionary
        }
    
        // Calculate the score for the word placement
        getWords(word);
        int score = getScore(word);
        System.out.println(score);
        // Place the word on the board (implementation details depend on your game logic)
        // placeWord(word);
        return score;
    }
    }