package com.java.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.model.ExchangeRate;
import com.java.exam.service.ExchangeRateService;

@RestController
@RequestMapping("/exchange-rate")
public class ExchangeRateController {

	@Autowired
	private ExchangeRateService ers;
	
	@GetMapping()
	public ResponseEntity<?> getExchangeRate(@RequestParam(value = "currAcronym", defaultValue = "PHP") String currAcronym) throws Exception{
		return(new ResponseEntity<ExchangeRate>(ers.getExchangeRate(currAcronym),HttpStatus.OK));
	}
}
