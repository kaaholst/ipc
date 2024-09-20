package org.example.ipc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    public static void main(String[] args) throws IOException {
        SocketAddress sa = new InetSocketAddress("localhost", Server.PORT);
        Socket socket = new Socket();

        socket.connect(sa, 10 * 1000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("login");
        out.println("hello");

        out.close();
        socket.close();
    }

}
