package com.poly.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;

@Controller
public class LoadPage {

	@Autowired
	HttpServletRequest request;
	/*
	 * @Autowired private ShoppingCartDAO shoppingCartDAO;
	 * 
	 * @Autowired SessionService sessionService;
	 * 
	 * @Autowired ProductDAO productDAO;
	 */

//	@GetMapping({  "/contact.html", "/about.html"})
//	public String loadPage(HttpServletRequest request) {
//		String path = request.getServletPath();
//
//		if ("/contact.html".equals(path)) {
//			return "contact";
//
//		} else if ("/about.html".equals(path)) {
//			return "about";
//		}
//		return "error";
//	}

	@Autowired
	ProductDAO proDAO;

	@RequestMapping({ "/", "index.html" })
	public String index(Model model) {

		List<Product> products = proDAO.findAll();
		model.addAttribute("products", products);
		System.out.println(products);
		return "index";
	}
	@RequestMapping("/product.html")
	public String findAllProducts(Model model) {
		List<Product> products = proDAO.findAll();
		model.addAttribute("products", products);
		System.out.println(products);
		return "product.html";
	}

	@RequestMapping("index2.html")
	public String index2() {
		return "index2";
	}

	@RequestMapping("cart.html")
	public String cart() {
		return "cart";
	}

}
