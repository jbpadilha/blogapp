package com.padilha.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Post post;

	private String author;

	@Column(length = 4096)
	private String comment;

	private LocalDateTime creationDate;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String content) {
		this.comment = content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}
}
