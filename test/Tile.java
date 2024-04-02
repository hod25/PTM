package test;

import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;

    public Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

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
        private Tile[] tiles;

        private Bag() {
            quantities = new int[]{
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

        private int getScore(char letter) {
            // Logic to get score for each letter, you may implement it according to your game rules
            return 1; // Default score
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
            if (index >= 0 && index < 26 && quantities[index] < 9) { // Assuming max quantity is 9, change according to your rules
                quantities[index]++;
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
            // int[] copy= new int[tiles.length];
            // System.arraycopy(tiles, 0, copy, 0, tiles.length);
            // return copy;
            // System.out.println(quantities.clone());
            return quantities.clone();
        }
    }
}
