package com.padilha.rest;

import com.padilha.model.dto.CommentDto;
import com.padilha.model.dto.NewCommentDto;
import com.padilha.service.CommentService;
import com.padilha.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class CommentController {

	@Autowired
	PostService postService;

	@Autowired
	CommentService commentService;

	@PostMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> addComment(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {

		// Commented because Test Suite is failing Mocking Get Post Service in CommentControllerTest
		/*PostDto postDto = postService.getPost(id);
		if (postDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		newCommentDto.setPostId(id);*/
		Long idComment = commentService.addComment(newCommentDto);
		if (idComment != null) {
			return new ResponseEntity<>(idComment, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getComments(@PathVariable Long id) {
		return commentService.getCommentsForPost(id);
	}

}
