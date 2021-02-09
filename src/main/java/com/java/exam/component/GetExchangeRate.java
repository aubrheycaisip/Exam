package com.java.exam.component;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.java.exam.model.ExchangeRate;

public class GetExchangeRate {
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public ExchangeRate pullExchangeRate(String symbol) throws Exception{
		ExchangeRate er = new ExchangeRate();
		try {
			er  = restTemplate.getForObject("https://api.frankfurter.app/latest?from="+ symbol +"&to=USD,EUR,PHP", ExchangeRate.class);
		}catch(HttpClientErrorException e) {
			throw new Exception("not acceptable acronym");
		}
		System.out.println(er);
		return er;
	}
}
