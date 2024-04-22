package test;
import java.io.BufferedReader; // Import the BufferedReader class
// import java.util.List; // Import the List class
import java.io.FileReader; // Import the FileReader class
import java.io.IOException; // Import the IOException class


public class Dictionary {
    private CacheManager existsCacheManager;
    private CacheManager notExistsCacheManager;
    private BloomFilter bloomFilter;

    public Dictionary(String... fileNames) {
        this.existsCacheManager = new CacheManager(400, new LRU());
        this.notExistsCacheManager = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5", "SHA1");
        for (String fileName : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        existsCacheManager.add(word);
                        bloomFilter.add(word);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean query(String word) {
        if (existsCacheManager.query(word)) {
            return true;
        } else if (notExistsCacheManager.query(word)) {
            return false;
        } else {
            boolean result = bloomFilter.contains(word);
            if (result) {
                existsCacheManager.add(word);
            } else {
                notExistsCacheManager.add(word);
            }
            return result;
        }
    }

    public boolean challenge(String word) {
        try {
            boolean result = IOSearcher.search(word, "text1.txt", "text2.txt"); // Assuming file names
            if (result) {
                existsCacheManager.add(word);
            } else {
                notExistsCacheManager.add(word);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}