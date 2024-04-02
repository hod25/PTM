package test;

import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;

    public Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    // Getter for the letter
    public char getLetter() {
        return letter;
    }
    // // Getter for the score
    // public int getScore() {
    //     return score;
    // } 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Tile tile = (Tile) obj;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        int result = (int) letter;
        result = 31 * result + score;
        return result;
    }

    public static class Bag {
        private static Bag instance = null;
        private int[] quantities;
        private int[] quantitiesD;
        private Tile[] tiles;

        private Bag() {
            quantities = new int[]{
                    9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2,
                    6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
            }; // Quantities for each letter A-Z
            quantitiesD = new int[]{
                9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2,
                6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
        }; // Quantities for each letter A-Z
            tiles = new Tile[26];
            for (int i = 0; i < 26; i++) {
                char letter = (char) ('A' + i);
                tiles[i] = new Tile(letter, getScore(letter));
            }
        }

        public static Bag getBag() {
            if (instance == null) {
                instance = new Bag();
            }
            return instance;
        }

        public int getScore(char letter) {
            switch (Character.toLowerCase(letter)) {
                case 'a':
                case 'e':
                case 'i':
                case 'n':
                case 'o':
                case 'r':
                case 's':
                case 't':
                case 'u':
                    return 1;
                case 'd':
                case 'l':
                case 'm':
                case 'g':
                    return 2;
                case 'b':
                case 'c':
                case 'p':
                    return 3;
                case 'f':
                case 'h':
                case 'v':
                case 'w':
                case 'y':
                    return 4;
                case 'k':
                    return 5;
                case 'j':
                case 'x':
                    return 8;
                case 'q':
                case 'z':
                    return 10;
                default:
                    return 0; // Handle characters not in the scoring system (e.g., whitespace)
            }
                    // return 1; // Default score
        }

        public Tile getRand() {
            if (size() == 0) {
                return null;
            }
            Random random = new Random();
            int index;
            do {
                index = random.nextInt(26);
            } while (quantities[index] == 0);
            quantities[index]--;
            return tiles[index];
        }

        public Tile getTile(char letter) {
            int index = letter - 'A';
            if (index < 0 || index >= 26 || quantities[index] == 0) {
                return null;
            }
            quantities[index]--;
            return tiles[index];
        }

        public void put(Tile tile) {
            int index = tile.letter - 'A';
            if (index >= 0 && index < 26 && quantities[index] < quantitiesD[index]) { // Assuming max quantity is 9, change according to your rules
                quantities[index]++;
                // System.out.println("Tile " + tile.letter + " is put back to bag");
                // System.out.println("Quantity of " + tile.letter + " is " + quantities[index]);
            }
        }

        public int size() {
            int count = 0;
            for (int quantity : quantities) {
                count += quantity;
            }
            return count;
        }

        public int[] getQuantities() {
            // int[] copy = new int[quantities.length];
            // for (int i = 0; i < quantities.length; i++) {
            //     copy[i] = quantities[i];
            // }
            // return copy;
            return quantities.clone();
        }
        
    }

}
