package com.java.exam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.exam.enums.ActivityEnum;
import com.sun.istack.Nullable;

import lombok.Data;

@Entity
@Data
@Table(name="history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ActivityEnum activity;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Nullable
	@Column(name="reciever_account_number")
	private Long recieverAccountNumber;
	
	@Nullable
	@Column(name="benefactor_account_number")
	private Long benefactorAccountNumber;
	
	@Nullable
	@Column(name="amount")
	private Double amount;
	
	@Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private Date dateCreated;
    
    @Column(name = "date_update", nullable = false, updatable = true)
    @UpdateTimestamp
    private Date lastUpdated;
}
