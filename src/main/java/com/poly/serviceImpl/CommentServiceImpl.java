package com.poly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CommentDAO;
import com.poly.entity.Comment;
import com.poly.entity.Reply;
import com.poly.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
    private CommentDAO commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

	@Override
	public List<Reply> findByReplyCommentId(Integer id) {
		
		return commentRepository.findByReplyCommentId(id);
	}
}
