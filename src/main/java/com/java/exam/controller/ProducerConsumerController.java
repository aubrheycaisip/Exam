package com.java.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.service.ProducerConsumerService;

@RestController
@RequestMapping("/producer-consumer")
public class ProducerConsumerController {
	
	@Autowired
	private ProducerConsumerService pcs;
	
	@GetMapping("/start")
	public ResponseEntity<?> producerConsumerStartProcess(){
		return new ResponseEntity<String>(pcs.startProcess(),HttpStatus.OK);
	}
	
	@GetMapping("/stop")
	public ResponseEntity<?> producerConsumerStopProcess(){
		return new ResponseEntity<String>(pcs.stopProcess(),HttpStatus.OK);
	}

}
