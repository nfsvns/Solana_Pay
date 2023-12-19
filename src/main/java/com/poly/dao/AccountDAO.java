package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String> {
	@Query("SELECT DISTINCT ar.account  FROM Authorities ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAdministrators();
	
	Account findByUsernameIgnoreCase(String username);
	
}
