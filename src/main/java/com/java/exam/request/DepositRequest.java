package com.java.exam.request;

import lombok.Data;

@Data
public class DepositRequest {

	private Double amount;
	private String accounNumber;
	private String recieverAccountNumber;
	
}
