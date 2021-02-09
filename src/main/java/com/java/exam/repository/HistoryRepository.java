package com.java.exam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.exam.model.History;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long>{

}
