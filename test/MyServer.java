package test;

// import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public final int port;
    public final ClientHandler clientHandler;
    private ServerSocket serverSocket;
    private boolean isRunning;

    public  MyServer(int port, ClientHandler clientHandler){
        this.port = port;
        this.clientHandler = clientHandler;
    }    
	public void start() {
        if (isRunning) {
            // System.out.println("Server is already running");
            return;
        }
        isRunning = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                // System.out.println("Server started on port " + port);

                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    // System.out.println("Client connected: " + clientSocket);

                    // Handle client connection using the provided ClientHandler
                    clientHandler.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());

                    // Close the client socket
                    clientSocket.close();
                }
            } catch (IOException e) {
                if (isRunning) {
                    // System.err.println("Error in the server: " + e.getMessage());
                }
            } finally {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        System.err.println("Error while closing server socket: " + e.getMessage());
                    }
                }
            }
        }).start();
    
    }
    public void close() {
        isRunning = false; 
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                // System.out.println("Server closed");
            }
        } catch (IOException e) {
            System.err.println("Error while closing server socket: " + e.getMessage());
        }
    }
}
