package ru.testapp;

import java.io.File;
import java.util.Stack;

public class SearchClass implements Runnable {

	private Thread thread;
	private String threadName;
	private String mask = "mask.txt";
	private File rootPath; // D://test
	private Stack<File> dirs = new Stack<File>();
	private WriteClass wrClass = new WriteClass("thread2");
	private static String path;

	public SearchClass(String threadName) {
		this.threadName = threadName;
	}

	public File getRootPath() {
		return rootPath;
	}

	public void setRootPath(File rootPath) {
		this.rootPath = rootPath;
	}

	public void run() {
		dirs.push(rootPath);
		try {
			do {
				for (File f : dirs.pop().listFiles()) {
					if (f.isDirectory()) {
						dirs.push(f);
					} else {
						if (f.getName().equals(mask)) {
							setPath(f.getPath());
							wrClass.start();
						}
					}
				}
			} while (!dirs.isEmpty());
		} catch (NullPointerException e) {
			System.out.println("All fine");
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	public static String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
