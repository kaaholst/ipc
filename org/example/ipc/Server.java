package org.example.ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 50124;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket;
        BufferedReader in;

        // Establish server socket
        serverSocket = new ServerSocket(PORT);
        serverSocket.setReuseAddress(true);

        // Wait for incoming connection
        System.out.println("Server listening on port: " + serverSocket.getLocalPort());
        socket = serverSocket.accept();

        System.out.println("Server connected to: " + socket.getRemoteSocketAddress());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()), 128);

        while (!Thread.interrupted()) {
            // Read data from Socket
            String line;
            try {
                line = in.readLine();
            } catch (IOException e) {
                line = null;
            }
            System.out.println("Server got: " + line);
            if (line == null) {
                break; // Client disconnected
            }
        }
        System.out.println("Server closing socket");
        socket.close();
        serverSocket.close();
    }

}
