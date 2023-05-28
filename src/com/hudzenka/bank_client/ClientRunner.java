package com.hudzenka.bank_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunner {

    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("127.0.0.1", 3333);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("MENU: Insert command and press Enter");
                String request = userInputReader.readLine();
                if ("exit".equals(request)) {
                    out.println(request);
                    break;
                } else {
                    out.println(request);
                }
                System.out.println("MENU: Waiting for response");
                String response = in.readLine();
                System.out.println("SERVER: " + response);
                if (matchesExit(response)) {
                    break;
                }
            }
        }
    }

    private static boolean matchesExit(String response) {
        return "Connection was closed because of timeout in request queue".equals(response);
    }
}