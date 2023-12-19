package com.poly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;
@Service
public class ProductServiceImpl  implements ProductService{
	@Autowired
	ProductDAO dao;
	public List<Product> findAll() {
		return dao.findAll();
	}
	
	public Product findById(Integer id) {
		return dao.findById(id).get();
	}

	public Product create(Product product) {
		return dao.save(product);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> sortProductASC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> sortProductDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> sortPriceLowToHight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> sortPriceHightToLow() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
