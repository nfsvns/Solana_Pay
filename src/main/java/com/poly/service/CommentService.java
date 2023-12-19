package com.poly.service;

import java.util.List;

import com.poly.entity.Comment;
import com.poly.entity.Reply;

public interface CommentService {
	List<Comment> findAll();

	Comment findById(Integer id);

	void create(Comment comment);

	Comment update(Comment comment);

	List<Reply> findByReplyCommentId(Integer id);

	void delete(Integer id);
}
