package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String s = in.readLine();
                    if (!s.contains("=Exit") && !s.contains("=Hello")) {
                        out.write("What".getBytes());
                    } else if (s.contains("=Hello")) {
                        out.write("Hello".getBytes());
                    } else if (s.contains("=Exit")) {
                        out.write("Server is closing".getBytes());
                        server.close();
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Something wrong with the stream", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Something wrong with the server", e);
        }
    }
}
