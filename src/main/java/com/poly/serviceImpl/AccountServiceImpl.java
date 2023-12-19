package com.poly.serviceImpl;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO dao;
    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public Account findById(String username) {
        return dao.findById(username).get();
    }

    @Override
    public Account create(Account account) {
        return dao.save(account);
    }

    @Override
    public Account update(Account account) {
        return dao.save(account);
    }

    @Override
    public void delete(String username) {
    	dao.deleteById(username);
    }
}
