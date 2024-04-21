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
    boolean isFirst = false;
    boolean isFirstWord = true; //only for the star tile
    private final HashMap<Integer,List<String>> squaresMap;

    private Board() {
        // Initialize the board with null tiles
        tiles = new Tile[BOARD_SIZE][BOARD_SIZE]; // Board is empty at the start 
        squaresMap = new HashMap<>();
        for (int i=0; i<=4; i++){
            squaresMap.put(i, new ArrayList<>());
        }

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
        int len = word.getTile().length;
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

    public boolean boardLegal(Word word) {
        int row = word.getRow();
        int col = word.getCol();  
        int len = word.getTile().length;
        boolean isVertical = word.isVertical();
        if (isInBound(word)) {
            if (isVertical) {
                if (col == 7 && row <= 7 && row+len >= 7) {
                    return true;
                }
                for (int i = row; i < len; i++) {
                    if (tiles[row+i][col] != null) {
                        return true;
                    }
                }
                for (int i = col; i < len+row; i++){
                    if ((tiles[i][col+1] != null)||(tiles[i][col-1] != null)) {
                        return true;
                    }
                }
            }
            else{ 
                if (row == 7 && col <= 7 && col+len >= 7) {
                    return true;
                }
                for (int i = col; i < len; i++) {
                    if (tiles[row][col+i] != null) {
                        return true;
                    }
                }
                for (int i = col; i < len+col; i++){
                    if ((tiles[row-1][i] != null)||(tiles[row+1][i] != null)) {
                        return true;
                    }
                }
            }
        }  
        return false;
    }    

    // Check if the word is legal according to the game dictionary
    public boolean dictionaryLegal(Word word) {return true;}// For now, always return true

    // Calculate the total score of the word, including bonuses
    public int getScore(Word word) {
        // Implement logic to calculate the score based on word placement
        Tile[] tiles = word.getTile();
        int tileScore = 0;
        int scoreSum = 0;
        int multiplyWord = 1;


        for (int i=0; i<word.getTile().length; i++) {
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
                case 5:// Double Word
                    scoreSum += tileScore;
                    multiplyWord *= 2;
                    break;
                default:
                    break;
            }
        }
        return scoreSum*multiplyWord;
    }
    
        // Method to get the bonus multiplier for each tile position
    private int getTileMultiplier(int row, int col) {
        if ((row == 0 && col == 0) || (row == 14 && col == 0) || (row == 7 && (col == 0 || col == 14))
        || (col == 7 && (row == 0 || row == 14)) || (row == 14 && col == 14) || (row == 0 && col == 7)) {
        return 3; // Triple Word
        } else if ((row == 1 && col == 1) || (row == 2 && col == 2) || (row == 3 && col == 3) || (row == 4 && col == 4)
        || (row == 1 && col == 13) || (row == 2 && col == 12) || (row == 3 && col == 11) || (row == 4 && col == 10)
        || (row == 13 && col == 1) || (row == 12 && col == 2) || (row == 11 && col == 3) || (row == 10 && col == 4)
        || (row == 10 && col == 10) || (row == 11 && col == 11) || (row == 12 && col == 12) || (row == 13 && col == 13)) {
        return 2; // Double Word
        } else if ((row == 1 && (col == 5 || col == 9)) || (row == 5 && (col == 1 || col == 5 || col == 9 || col == 13))
        || (row == 9 && (col == 1 || col == 5 || col == 9 || col == 13)) || (row == 13 && (col == 5 || col == 9))) {
        return 1; // Triple Letter
        } else if ((row == 3 && (col == 0 || col == 14)) || (col == 3 && (row == 0 || row == 14))
        || (row == 0 && col == 11) || (row == 2 && (col == 6 || col == 8)) || (row == 6 && (col == 2 || col == 6 || col == 8 || col == 12))
        || (row == 7 && (col == 3 || col == 12)) || (row == 8 && (col == 2 || col == 6 || col == 8 || col == 12))
        || (row == 11 && (col == 0 || col == 7 || col == 14)) || (row == 12 && (col == 6 || col == 8)) || (row == 14 && (col == 3 || col == 11))) {
        return 4; // Double Letter   
        }else if ((row == 7 && col == 7)&&(isFirstWord)){
            isFirstWord = false;
             return 5; // Star Letter  
        } else {
            return 0; // No Bonus
        }
    }
    
    public int tryPlaceWord(Word word) {
        int score=0;
        if (!boardLegal(word)) {
            // boardIsEmpty = false;
            // System.out.println("board is not Legal ");
           return -1; // Word placement is not legal
        }
        if (word.isVertical()){ 
            int j=word.getCol();
            for (int i=word.getRow(); i<word.getRow()+word.tiles.length; i++){
                if (tiles[i][j]==null){ 
                    tiles[i][j]=word.tiles[i-word.getRow()];
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
        if (!dictionaryLegal(word)) {
            return -1; // Word is not legal according to the dictionary
        }
        isFirst=false;
        // Calculate the score for the word placement
        ArrayList<Word> newWords = getWords(word);
        for (Word w : newWords) {
            score += getScore(w);
            // System.out.println(score);
            }
        return score;
    }
        // Get an array of all new words that will be created by placing the given word
        public ArrayList<Word> getWords(Word word) {
            ArrayList<Word> newWords = new ArrayList<>();
            newWords.add(word);
            int row = word.getRow();
            int col = word.getCol();
            int len = word.getTile().length;
            if (word.isVertical()) {
                for (int i = row; i < len; i++) {
                    if ((tiles[i][col] != null)&&(word.tiles[i-row]!=null)) {
                        // Check if a new word is created horizontally
                        int j = col;
                        while (j >= 0 && tiles[i][j] != null) {
                            j--;
                        }
                        j++;
                        int start = j;
                        while (j < BOARD_SIZE && tiles[i][j] != null) {
                            j++;
                        }
                        if (j - start > 1) {
                            Tile[] newTiles = new Tile[j - start];
                            for (int k = start; k < j; k++) {
                                newTiles[k - start] = tiles[i][k];
                            }
                            newWords.add(new Word(newTiles, i, start, false));
                        }
                    }
                }
            } else {
                for (int i = col; i < len+col; i++) {
                    if ((tiles[row][i] != null)&&(word.tiles[i-col]!=null)) {
                        // Check if a new word is created vertically
                        int j = row;
                        while (j >= 0 && tiles[j][i] != null) {
                            j--;
                        }
                        j++;
                        int start = j;
                        while (j < BOARD_SIZE && tiles[j][i] != null) {
                            j++;
                        }
                        if (j - start > 1) {
                            Tile[] newTiles = new Tile[j - start];
                            for (int k = start; k < j; k++) {
                                newTiles[k - start] = tiles[k][i];
                            }
                            newWords.add(new Word(newTiles, start, i, true));
                        }
                    }
                }
            }
            // Implement logic to find new words created by placing the given word
            return newWords;
        }
    }