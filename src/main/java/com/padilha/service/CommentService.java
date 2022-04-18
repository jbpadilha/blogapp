package com.padilha.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.padilha.model.Comment;
import com.padilha.model.Post;
import com.padilha.repository.CommentRepository;
import com.padilha.repository.PostRepository;
import com.padilha.model.dto.CommentDto;
import com.padilha.model.dto.NewCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
    CommentRepository commentRepository;

	@Autowired
    PostRepository postRepository;

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		return commentRepository.findAll()
			.stream().map(comment -> new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(), comment.getCreationDate()))
			.sorted(Comparator.comparing(CommentDto::getCreationDate))
			.collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) {

		if(newCommentDto.getPostId() == null) {
			throw new IllegalArgumentException();
		}

		//Post
		Post post = postRepository.getOne(newCommentDto.getPostId());

		Comment comment = new Comment();
		comment.setPost(post);
		comment.setComment(newCommentDto.getContent());
		comment.setCreationDate(LocalDateTime.now());
		comment.setAuthor(newCommentDto.getAuthor());
		Comment newComment = commentRepository.save(comment);
		return newComment.getId();
	}
}
