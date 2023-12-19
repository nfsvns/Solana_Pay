package com.poly.service;

import java.util.List;

import com.poly.entity.Authorities;

public interface AuthorityService {
	public List<Authorities> findAll() ;

	public Authorities create(Authorities auth) ;

	public void delete(Integer id) ;

	public List<Authorities> findAuthoritiesOfAdministrators() ;

}
