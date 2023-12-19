package com.poly.entity;

import java.io.Serializable;

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
@Table(name = "Authorities")
public class Authorities implements Serializable {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	     Integer id; 
	    @ManyToOne
	    @JoinColumn(name = "Username") 
	     Account account;
	    @ManyToOne 
	    @JoinColumn(name = "roleid")
	     Role role;
	     

}