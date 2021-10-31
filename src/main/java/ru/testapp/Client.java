package ru.testapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {
	private static Socket clientSocket;
	private static BufferedReader reader;
	private static BufferedReader in;
	private static BufferedWriter out;

	public static void main(String[] args) {
		try {
			try {
				clientSocket = new Socket("localhost", 8080);
				reader = new BufferedReader(new InputStreamReader(System.in));
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

				System.out.println("Type a path:");
				String word = reader.readLine();
				out.write(word + "\n");
				out.flush();
			} finally {
				System.out.println("Client is down");
				clientSocket.close();
				in.close();
				out.close();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}