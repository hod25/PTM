package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
public class BookScrabbleHandler implements ClientHandler {
    public void handleClient(InputStream input, OutputStream output) {
        boolean result = false;
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true)
        ) {
            String inputLine = reader.readLine();
            System.out.println(inputLine);
            if (inputLine != null) {
                String[] parts = inputLine.split(",");
                String queryType = parts[0];
                String[] subsetOfBooks = Arrays.copyOfRange(parts, 1, parts.length);
                if (queryType.equals("Q")) {
                    DictionaryManager dictionaryManager = new DictionaryManager();
                    if(dictionaryManager.query(subsetOfBooks)) result = true;
                } else if (queryType.equals("C")) {
                    DictionaryManager dictionaryManager = new DictionaryManager();
                    if(dictionaryManager.challenge(subsetOfBooks)) result = true;
            }
        }
            writer.println(String.valueOf(result));
        }
        catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
    @Override
    public void close() {}
}

