package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Transaction;

public interface TransactionDAO extends JpaRepository<Transaction, Integer>{

}
