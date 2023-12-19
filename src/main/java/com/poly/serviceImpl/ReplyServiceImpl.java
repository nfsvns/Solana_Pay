package com.poly.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ReplyDAO;
import com.poly.entity.Reply;
import com.poly.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO replyRepository;

	@Override
	public List<Reply> findAll() {
		return replyRepository.findAll();
	}

	@Override
	public Reply findById(Integer id) {
		return replyRepository.findById(id).orElse(null);
	}

	@Override
	public void create(Reply reply) {
		replyRepository.save(reply);
	}

	@Override
	public Reply update(Reply reply) {
		return replyRepository.save(reply);
	}

	@Override
	public void delete(Integer id) {
		replyRepository.deleteById(id);
	}
}
