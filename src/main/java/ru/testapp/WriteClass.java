package ru.testapp;

public class WriteClass implements Runnable {

	private String threadName;
	public static Thread thread;

	public WriteClass(String threadName) {
		this.threadName = threadName;
	}

	public void run() throws IllegalMonitorStateException {
		System.out.println(SearchClass.getPath());
	}

	public void start() {
		System.out.println("Starting " + threadName);
		thread = new Thread(this, threadName);
		if (!thread.isAlive()) {
			thread.start();
		}
	}
}
