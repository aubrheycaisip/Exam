package com.java.exam.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="exchange_rate")
public class ExchangeRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private String base;
	
	private String date;
	
	@ElementCollection
	@CollectionTable(name = "exchange_rate_mapping", 
    	joinColumns = {@JoinColumn(name = "exchange_rate_id", referencedColumnName = "id")})
	@MapKeyColumn(name = "symbol")
    @Column(name = "rate")
	private Map<String,Double> rates;
	
}