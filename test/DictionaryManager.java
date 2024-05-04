package test;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
public class DictionaryManager {
    private static DictionaryManager instance;
    private Dictionary dictionary;  
    // private String map;
    private Map<String, Dictionary> DictionaryMap = new HashMap<>();

    // private DictionaryManager() {
    //     dictionary = new Dictionary("t1.txt", "t2.txt", "t3.txt");
    // }

    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }
    //
    // public boolean query(String file1, String file2, String word) {
    //     return dictionary.query(word);
    // }
    //
    // public boolean challenge(String file1, String file2, String file3, String word) {
    //     return dictionary.challenge(word);
    // }

    public int getSize() {
        // System.out.println(dictionary.getClass().getDeclaredFields().length);
        return dictionary.getClass().getDeclaredFields().length;
    }

    public boolean query(String... args) {
        String queryWord = args[args.length - 1];
        boolean result = false;
        for (int i = 0; i < args.length - 1; i++) {
            String book = args[i];
            // Dictionary dictionary = DictionaryMap.get(book);
            if (!DictionaryMap.containsKey(book)) 
                DictionaryMap.put(book, new Dictionary(book)); 
        }
        // for (String book : args) {
        //     try {
        //         if (IOSearcher.search(queryWord, book))
        //             result=true; 
        //     } catch (IOException e) {
        //         // Handle the IOException here
        //         e.printStackTrace();
        //     }
        // }  
    //     for (int i = 0; i < args.length - 1; i++) {
    //         String book = args[i];
    //         Dictionary dictionary = DictionaryMap.get(book);

    //         if (dictionary == null) {
    //             dictionary = new Dictionary(book);
    //             DictionaryMap.put(book, dictionary);
    //         }

    //         if (dictionary.query(queryWord)) {
    //             result = true;
    //         }
    //     }
    
        return result;
    }

    // public boolean challenge(String... args) {
    //     String challengeWord = args[args.length - 1];
    //     boolean result = false;
        
    //     for (int i = 0; i < args.length - 1; i++) {
    //         String book = args[i];
    //         Dictionary dictionary = dictionaries.get(book);
    
    //         if (dictionary == null) {
    //             dictionary = new Dictionary(book);
    //             dictionaries.put(book, dictionary);
    //         }
    
    //         // Perform the challenge operation on the dictionary
    //         if (dictionary.challenge(challengeWord)) {
    //             result = true;
    //         }
    //     }
    //     return result;
    // }

    public boolean challenge(String... args) {
        String challengeWord = args[args.length - 1];
        boolean result = false;

        for (int i = 0; i < args.length - 1; i++) {
            String book = args[i];
            // Dictionary dictionary = DictionaryMap.get(book);
            if (!DictionaryMap.containsKey(book)) 
                DictionaryMap.put(book, new Dictionary(book));
        }
        for (String book : args) {
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

    //     public boolean challenge(String... args) {
    //     String challengeWord = args[args.length - 1];
    //     boolean result = false;
        
    //     for (int i = 0; i < args.length - 1; i++) {
    //         String book = args[i];
    //         Dictionary dictionary = DictionaryMap.get(book);
    //         if (!DictionaryMap.containsKey(book)) 
    //             DictionaryMap.put(book, new Dictionary(book));
    //     }
    //     for (String book : args) {
    //         // String book = args[i];
    //         // Dictionary key = DictionaryMap.get(book);
    //         if (IOSearcher.search(challengeWord, book))
    //             result=true; 
    //     }  
    //     return result;
        
    // }
}
