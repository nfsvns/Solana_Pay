package com.poly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.entity.Account;
import com.poly.entity.Authorities;
import com.poly.service.AuthorityService;


@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO accountDAO;

	public List<Authorities> findAll() {
		return dao.findAll();
	}

	public Authorities create(Authorities auth) {
		return dao.save(auth);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Authorities> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.getAdministrators();
		return dao.authoritiesOf(accounts);
	}
}
