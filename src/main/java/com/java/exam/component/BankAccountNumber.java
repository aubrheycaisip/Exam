package com.java.exam.component;

import java.util.Random;

public class BankAccountNumber {

	public Long generateBankAccountNumber() {
		Random rand = new Random();
	    String card = "";
	    for (int i = 0; i < 14; i++)
	    {
	        int n = rand.nextInt(10) + 0;
	        card += Integer.toString(n);
	    }
	    return Long.parseLong(card);
	}
}
