package com.poly.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDAO;
import com.poly.dao.CommentDAO;
import com.poly.dao.ProductDAO;
import com.poly.dao.ReplyDAO;
import com.poly.entity.Account;
import com.poly.entity.Comment;
import com.poly.entity.Product;
import com.poly.entity.Reply;


@Controller
public class ProductController {
	
	@Autowired
	ProductDAO dao;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	ReplyDAO replyDAO;
	
	@RequestMapping("/shop-single.html/{productId}")
	public String getProduct(Model model, @PathVariable("productId") int productId) {
		Product list = dao.findById(productId).get();
		List<Comment> comment = commentDAO.findByCommentId(productId);

		List<Reply> reply = replyDAO.findByCommentProductId(productId);
		model.addAttribute("prod", list);
		model.addAttribute("reply", reply);
		model.addAttribute("comment", comment);
		// Chắc chắn rằng listS chứa thông tin về số lượng của size
		return "shop-single";
	}
	
	@PostMapping("/shop.html/addComments")
	public String addComment(@RequestParam("description") String description,
			@RequestParam(value = "productId", required = false) Integer idProduct, Model model,
			HttpServletRequest request) {
		Comment commentt = new Comment();
		// add Comment
		// getTime();
		Timestamp now = new Timestamp(new Date().getTime());
		// getUserName();
		String username = request.getRemoteUser();
		Account user = accountDAO.findById(username).orElse(null);
		// setDescription
		commentt.setDescription(description); // Set the comment text
		// setAccountUsername
		commentt.setAccount(user);
		// setDate
		commentt.setCreate_Date(now);

		// check product
		if (idProduct != null) {
			Product product = dao.findById(idProduct).orElse(null);
			if (product != null) {
				// if productId != null
				commentt.setProduct(product);
			} else {
				throw new IllegalArgumentException("Product with id " + idProduct + " not found!");
			}
		} else {
			// Handle when productId is null
			throw new IllegalArgumentException("Product id is null!");
		}

		// add comments
		commentDAO.save(commentt);

		// Fetch data again after saving the new comment
		Product list = dao.findById(idProduct).orElse(null);
		List<Comment> comment = commentDAO.findByCommentId(idProduct);

		model.addAttribute("prod", list);
		model.addAttribute("comment", comment);

		List<Reply> reply = replyDAO.findByCommentProductId(idProduct);

		model.addAttribute("reply", reply);
		// Add a success message to the model
		model.addAttribute("message", "Comment added successfully!");
		 // Truy vấn danh sách hãng và số lượng sản phẩm tương ứng
		// Return the name of your success template
		 return "redirect:/shop-single.html/" + idProduct;
	}

	@PostMapping("/shop.html/replyComments")
	public String replyComment(@RequestParam("descriptionReply") String description,
			@RequestParam(value = "productIdReply", required = false) Integer idProduct,
			@RequestParam(value = "parentId", required = false) Integer idComment, Model model,
			HttpServletRequest request) {
		Reply commentt = new Reply();
		Timestamp now = new Timestamp(new Date().getTime());
		String username = request.getRemoteUser();
		Account user = accountDAO.findById(username).orElse(null);

		commentt.setDescription(description); // Set the comment text
		commentt.setAccount(user);

		commentt.setCreate_date(now);

		if (idProduct != null) {
			Product product = dao.findById(idProduct).orElse(null);
			if (product != null) {
				commentt.setProduct(product);
			} else {
				throw new IllegalArgumentException("Product with id " + idProduct + " not found!");
			}
		} else {
			// Handle when productId is null
			throw new IllegalArgumentException("Product id is null!");
		}

		if (idComment != null) {
			Comment comment = commentDAO.findById(idComment).orElse(null);
			if (comment != null) {
				commentt.setComment(comment);
			} else {
				throw new IllegalArgumentException("Product with id " + idProduct + " not found!");
			}
		} else {
			// Handle when productId is null
			throw new IllegalArgumentException("Product id is null!");
		}

		replyDAO.save(commentt);

		// Fetch data again after saving the new comment
		Product list = dao.findById(idProduct).orElse(null);
		List<Comment> comment = commentDAO.findByCommentId(idProduct);

		model.addAttribute("prod", list);
		model.addAttribute("comment", comment);
		List<Reply> reply = replyDAO.findByCommentProductId(idProduct);

		model.addAttribute("reply", reply);
		// Add a success message to the model
		model.addAttribute("message", "Comment added successfully!");
		 return "redirect:/shop-single.html/" + idProduct;
	}
	
	
}
