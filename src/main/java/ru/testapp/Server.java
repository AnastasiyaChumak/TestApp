package ru.testapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private static Socket clientSocket;
	private static ServerSocket server;
	private static BufferedReader in;
	private static BufferedWriter out;

	public static void main(String[] args) {
		String word = "";
		SearchClass clas = new SearchClass("thread1");
		try {
			try {
				server = new ServerSocket(8080);
				System.out.println("Server is working now");
				clientSocket = server.accept();
				try {
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					word = in.readLine();
					System.out.println(word);

					File path = new File(word);
					clas.setRootPath(path);
					clas.start();
					System.out.println(clas.getRootPath());

					out.flush();

				} finally {
					clientSocket.close();
					in.close();
					out.close();
				}
			} finally {
				System.out.println("Server is down");
				server.close();
			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
