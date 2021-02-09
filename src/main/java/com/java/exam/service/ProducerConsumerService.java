package com.java.exam.service;

import org.springframework.stereotype.Service;

import com.java.exam.component.Consumer;
import com.java.exam.component.Producer;

@Service
public class ProducerConsumerService {

	public static Producer producer = new Producer();
	public static Consumer consumer = new Consumer(producer);
	public static Consumer consumer1 = new Consumer(producer);
	
	public String startProcess() {
		if(!producer.isAlive()) {
			producer.setName("Producer1");
			producer.setDaemon(true);
			producer.start();
		}
		if(!consumer.isAlive()) {
			consumer.setName("Consumer1");
			consumer.setDaemon(true);
			consumer.start();
		}
		if(!consumer1.isAlive()) {
			consumer1.setName("Consumer2");
			consumer1.setDaemon(true);
			consumer1.start();
		}
		return "procedure consumer process is running";
	}

	@SuppressWarnings("deprecation")
	public String stopProcess() {
		if(producer.isAlive()) {
			producer.stop();
		}
		if(consumer.isAlive()) {
			consumer.stop();
		}
		if(consumer1.isAlive()) {
			consumer1.stop();
		}
		return "procedure consumer process is terminated";
	}
}
