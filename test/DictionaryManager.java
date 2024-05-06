package test;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Arrays;
public class DictionaryManager {
    private static DictionaryManager instance;
    private Map<String, Dictionary> DictionaryMap = new HashMap<>();


    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    public int getSize() {
        return DictionaryMap.size();
    }

    public boolean query(String... books) {
        String queryWord = books[books.length - 1];
        boolean result = false;
        String[] booksNames = Arrays.copyOfRange(books, 0, books.length - 1);
        for (String book : booksNames) {
            // Dictionary dictionary = DictionaryMap.get(book);
            if (!DictionaryMap.containsKey(book)) DictionaryMap.put(book, new Dictionary(book));
            }
        for (String book : booksNames) {
            Dictionary dictionary = DictionaryMap.get(book);
            if (dictionary != null && dictionary.query(queryWord)) {
                result = true;
            }        }
        return result;
    } 
    
    public boolean challenge(String... books) {
        String challengeWord = books[books.length - 1];
        boolean result = false;
        String[] booksNames = Arrays.copyOfRange(books, 0, books.length - 1);
        for (String book : booksNames) {
            // Dictionary dictionary = DictionaryMap.get(book);
            if (!DictionaryMap.containsKey(book)) DictionaryMap.put(book, new Dictionary(book));
        }
        for (String book : booksNames) {
            try {
                if (IOSearcher.search(challengeWord, book))
                    result = true; 
            } catch (IOException e) {
                // Handle the IOException here
                e.printStackTrace();
            }
        }  
        return result;
        
    }
}
