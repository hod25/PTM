package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import test.DictionaryManager;
// public class BookScrabbleHandler {


public class BookScrabbleHandler implements ClientHandler {

    public void handleClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine = reader.readLine();
            if (inputLine != null) {
                String[] parts = inputLine.split(",");
                    String queryType = parts[0];
                    String[] words = parts[1].split(",");
                    if (queryType.equals("Q")) {
                        String queryWord = words[words.length - 1].trim();
                        DictionaryManager dictionaryManager = new DictionaryManager();
                        boolean result = dictionaryManager.query(queryWord);
                        writer.println(result + "-1");
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
        catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
    public void close() {
        // Implementation of the close() method.
    }
}
