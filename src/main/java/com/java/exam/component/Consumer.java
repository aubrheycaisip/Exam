package com.java.exam.component;

public class Consumer extends Thread{

	private Producer producer;
	
	public Consumer(Producer producer) {
		this.producer = producer;
	}
	
	public void run() {
		try {
			while(true) {
				String data = producer.consume();
				System.out.println("Data " + data + " is consume by " + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
