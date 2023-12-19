package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.BankAccount;

public interface BankAccountDAO extends JpaRepository<BankAccount, Integer>{

}
