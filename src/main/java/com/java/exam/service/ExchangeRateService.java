package com.java.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.exam.component.GetExchangeRate;
import com.java.exam.model.ExchangeRate;
import com.java.exam.repository.ExchangeRateRepository;

@Service
public class ExchangeRateService {
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	GetExchangeRate ger = new GetExchangeRate();

	public ExchangeRate getExchangeRate(String symbol) throws Exception{
		ExchangeRate er = ger.pullExchangeRate(symbol);
		exchangeRateRepository.save(er);
		return er;
	}
}
