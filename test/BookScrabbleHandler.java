package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
// public class BookScrabbleHandler {


public class BookScrabbleHandler {
    private final DictionaryManager dictionaryManager;

    public BookScrabbleHandler(DictionaryManager dictionaryManager) {
        this.dictionaryManager = dictionaryManager;
    }

    public void handleClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine = reader.readLine();
            if (inputLine != null) {
                String[] parts = inputLine.split(",");
                if (parts.length >= 2) {
                    String queryType = parts[0];
                    String[] words = parts[1].split(",");
                    if (queryType.equals("Q")) {
                        // String queryWord = words[words.length - 1].trim();
                        // boolean result = dictionaryManager.contains(queryWord);
                        // writer.println(result + "-1");
                    } else if (queryType.equals("C")) {
                        // Handle challenge
                        // This part is not implemented as it's not clear what the challenge is
                        
                        writer.println("Challenge not implemented.");
                    } else {
                        writer.println("Invalid query type.");
                    }
                } else {
                    writer.println("Invalid input format.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
