package com.poly.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Reply;
import com.poly.service.ReplyService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/replies")
public class ReplyRestController {

	@Autowired
	private ReplyService replyService;

	@GetMapping
	public List<Reply> getAllReplies() {
		return replyService.findAll();
	}

	@GetMapping("/{id}")
	public Reply getReplyById(@PathVariable("id") Integer id) {
		return replyService.findById(id);
	}

	@PostMapping
	public Reply createReply(@RequestBody Reply reply) {
		replyService.create(reply);
		return reply;
	}

	@PutMapping("/{id}")
	public Reply updateReply(@PathVariable("id") Integer id, @RequestBody Reply reply) {
		reply.setId(id);
		return replyService.update(reply);
	}

	@DeleteMapping("/{id}")
	public void deleteReply(@PathVariable("id") Integer id) {
		replyService.delete(id);
	}
}
