package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static Board instance = null;
    private final int SIZE = 15; // 15x15 game board
    private final Board[][] boards;
    private final List<String> tripleWordBonuses;
    private final List<String> doubleWordBonuses;
    private final List<String> tripleLetterBonuses;
    private final List<String> doubleLetterBonuses;

    private Board() {
        boards = new Board[SIZE][SIZE];

        // Initialize bonus coordinates
        tripleWordBonuses = Arrays.asList("1,1", "15,1", "8,1", "8,15", "15,1", "15,15", "1,8", "15,8");
        doubleWordBonuses = Arrays.asList(
            "2,2", "3,3", "4,4", "5,5", "2,14", "3,13", "4,12", "5,11", 
            "14,2", "13,3", "12,4", "11,5", "11,11", "12,12", "13,13", "14,14", "8,8"
        );
        tripleLetterBonuses = Arrays.asList(
            "2,6", "2,10", "6,2", "6,6", "6,10", "6,14", "10,2", "10,6", "10,10", "10,14", "14,6", "14,10"
        );
        doubleLetterBonuses = Arrays.asList(
            "4,1", "4,1", "1,12", "4,15", "3,7", "4,8", "3,9", "7,3", "7,7", "7,9", 
            "7,13", "8,4", "8,14", "9,3", "9,7", "9,9", "9,13", "12,1", "12,8", "12,15", 
            "13,7", "13,9", "15,4", "15,12"
        );
    }

    public static Board getBoard() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public Tile[][] getTiles() {
        // Return a copy of the tiles array
        Tile[][] copy = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(boards[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    public boolean boardLegal(Word word) {
        // Check if the whole word is inside the board
        int row = word.getRow();
        int col = word.getCol();
        if (row < 0 || col < 0 || row >= SIZE || col >= SIZE) {
            return false;
        }

        // Check if the word rests on an existing tile
        if (boards[row][col] != null) {
            return true;
        }

        // Check adjacent or overlapping tiles
        if (word.isVertical()) {
            for (int i = 0; i < word.getTiles().length; i++) {
                if (row + i < SIZE && boards[row + i][col] != null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < word.getTiles().length; i++) {
                if (col + i < SIZE && boards[row][col + i] != null) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dictionaryLegal(Word word) {
        // For now, always return true
        return true;
    }

    public ArrayList<Word> getWords(Word word) {
        // Return an ArrayList containing the word itself
        ArrayList<Word> words = new ArrayList<>();
        words.add(word);
        return words;
    }

    public int getScore(Word word) {
        // Calculate the total score of the word, including bonuses
        int score = 0;
        // Implement scoring logic based on board bonuses
        return score;
    }

    public int tryPlaceWord(Word word) {
        if (boardLegal(word)) {
            ArrayList<Word> newWords = getWords(word);
            for (Word w : newWords) {
                if (!dictionaryLegal(w)) {
                    return 0; // If any word is not legal, return 0 score
                }
            }
            // Perform the actual placement on the board
            // Calculate and return the cumulative score for each new word created
            System.out.println("Placing word: " + Arrays.toString(word.getTiles()));
            return getScore(word);
        } else {
            return 0; // If the word is not legal on the board, return 0 score
        }
    }
}

