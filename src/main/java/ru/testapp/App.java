package ru.testapp;

import java.io.File;
import java.util.Stack;

public class App extends Thread {
	
	String mask = "mask.txt";
	static File rootPath = new File("D://");
	static Stack<File> dirs = new Stack<File>();

	
	public static void main(String[] args) {
		dirs.push(rootPath);
		(new App()).start();
	}

	@Override
	public void run() {
		do {
			for (File f : dirs.pop().listFiles()) {
				if (f.isDirectory()) {
					dirs.push(f);
				} else {
					if (f.getName().equals(mask))
						System.out.println(f.getPath());
				}
			}
		} while (!dirs.isEmpty());

	}
}