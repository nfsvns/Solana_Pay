package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Reply;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {

	@Query("SELECT o FROM Reply o where o.product.id = ?1")
	List<Reply> findByCommentProductId(Integer keywords);

}
