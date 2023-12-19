package com.poly.service;

import com.poly.entity.Account;
import com.poly.entity.Product;

import java.util.List;

public interface AccountService {
    public List<Account> findAll() ;

    public Account findById(String username) ;

    public Account create(Account account) ;

    public Account update(Account account) ;

    public void delete(String username) ;

}
