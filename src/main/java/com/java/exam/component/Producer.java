package com.java.exam.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread{

	private final int strListLimit = 5;
	private final List<String> stringList = new ArrayList<String>();
	
	public void run() {
		try {
			while(true) {
				produce();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized void produce() throws InterruptedException {
		while(stringList.size() == strListLimit) {
			wait();
		}
		String data = LocalDateTime.now().toString();
		stringList.add(data);
		System.out.println("Produce Data: " + data);
		notify();
	}
	
	public synchronized String consume() throws InterruptedException {
		notify();
		while(stringList.isEmpty()) {
			wait();
		}
		String data = stringList.get(0);
		stringList.remove(0);
		sleep(2000);
		return data;
		
	}
}
