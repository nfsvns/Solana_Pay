package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "BankAccounts")
public class BankAccount implements Serializable {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	     Integer accountID; 
	     String accountNumber;
	     String bankName;
	     String accountType;

	    @ManyToOne
	    @JoinColumn(name = "username")
	     Account account; 


}