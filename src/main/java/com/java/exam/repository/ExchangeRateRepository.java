package com.java.exam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.exam.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long>{

}
