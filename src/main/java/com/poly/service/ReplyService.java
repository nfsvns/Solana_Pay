package com.poly.service;

import java.util.List;

import com.poly.entity.Reply;

public interface ReplyService {
	List<Reply> findAll();

	Reply findById(Integer id);

	void create(Reply reply);

	Reply update(Reply reply);

	void delete(Integer id);
}
