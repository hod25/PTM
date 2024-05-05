package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import test.DictionaryManager;




public class BookScrabbleHandler implements ClientHandler {

    public void handleClient(InputStream input, OutputStream output) {
        boolean result = false;
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true)
        ) {
            String inputLine = reader.readLine();
            if (inputLine != null) {
                String[] parts = inputLine.split(",");
                String queryType = parts[0];
                String[] words = parts[1].split(",");
                if (queryType.equals("Q")) {
                    String queryWord = words[words.length - 1].trim();
                    DictionaryManager dictionaryManager = new DictionaryManager();
                    if(!dictionaryManager.query(queryWord)) 
                    result = true;
                    writer.println(String.valueOf(result)); // Changed this line
                } else if (queryType.equals("C")) {
                    // Handle challenge
                    String challengeWord = words[words.length - 1].trim();
                    DictionaryManager dictionaryManager = new DictionaryManager();
                    if(!dictionaryManager.challenge(challengeWord))
                    result = true;
                    writer.println(String.valueOf(result)); // Changed this line
                    // This part is not implemented as it's not clear what the challenge is
                    // writer.println("Challenge not implemented.");
                } else {
                    // writer.println("Invalid query type.");
                    // result = false;
                }
            } else {
                // writer.println("Invalid input format.");
                // result = false;
            }
        }
        catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        // Here you can close any resources that need to be closed.
        // Since this class doesn't open any resources, this method can be left empty.
    }
}

// public class BookScrabbleHandler implements ClientHandler {

//     public void handleClient(InputStream input, OutputStream output) {
//         try (
//             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//             PrintWriter writer = new PrintWriter(output, true)
//         ) {
//             String inputLine = reader.readLine();
//             if (inputLine != null) {
//                 String[] parts = inputLine.split(",");
//                     String queryType = parts[0];
//                     String[] words = parts[1].split(",");
//                     if (queryType.equals("Q")) {
//                         String queryWord = words[words.length - 1].trim();
//                         DictionaryManager dictionaryManager = new DictionaryManager();
//                         boolean result = dictionaryManager.query(queryWord);
//                         writer.println(result + "-1");
//                     } else if (queryType.equals("C")) {
//                         // Handle challenge
//                         // This part is not implemented as it's not clear what the challenge is
//                         writer.println("Challenge not implemented.");
//                     } else {
//                         writer.println("Invalid query type.");
//                     }
//                 } else {
//                     writer.println("Invalid input format.");
//                 }
//             }
//         catch (IOException e) {
//             System.err.println("Error handling client: " + e.getMessage());
//         }
//     }
//     @Override
//     public void close() {
//         // Here you can close any resources that need to be closed.
//         // Since this class doesn't open any resources, this method can be left empty.
//     }
// }
// public class BookScrabbleHandler implements ClientHandler {

//     public void handleClient(Socket clientSocket) {
//         try (
//             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
//         ) {
//             String inputLine = reader.readLine();
//             if (inputLine != null) {
//                 String[] parts = inputLine.split(",");
//                     String queryType = parts[0];
//                     String[] words = parts[1].split(",");
//                     if (queryType.equals("Q")) {
//                         String queryWord = words[words.length - 1].trim();
//                         DictionaryManager dictionaryManager = new DictionaryManager();
//                         boolean result = dictionaryManager.query(queryWord);
//                         writer.println(result + "-1");
//                     } else if (queryType.equals("C")) {
//                         // Handle challenge
//                         // This part is not implemented as it's not clear what the challenge is
//                         writer.println("Challenge not implemented.");
//                     } else {
//                         writer.println("Invalid query type.");
//                     }
//                 } else {
//                     writer.println("Invalid input format.");
//                 }
//             }
//         catch (IOException e) {
//             System.err.println("Error handling client: " + e.getMessage());
//         }
//     }
//     public void close() {
//         // Implementation of the close() method.
//     }
// }
