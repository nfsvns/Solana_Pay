package com.poly.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Comment;
import com.poly.entity.Reply;
import com.poly.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comments")
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;

	@GetMapping
	public List<Comment> getAllComments() {
		return commentService.findAll();
	}

	@GetMapping("/{id}")
	public Comment getCommentById(@PathVariable("id") Integer id) {
		return commentService.findById(id);
	}

	@PostMapping
	public Comment createComment(@RequestBody Comment comment) {

		commentService.create(comment);
		return comment;
	}

	@PutMapping("/{id}")
	public Comment updateComment(@PathVariable("id") Integer id, @RequestBody Comment comment) {
		comment.setId(id);
		return commentService.update(comment);
	}

	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<List<Reply>> getHistoryDetail(@PathVariable Integer id) {
		List<Reply> ReplyDetail = commentService.findByReplyCommentId(id);
		if (ReplyDetail != null) {
			return new ResponseEntity<>(ReplyDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ReplyDetail, HttpStatus.NOT_FOUND);
		}
	}
}
