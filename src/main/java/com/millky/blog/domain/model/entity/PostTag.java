package com.millky.blog.domain.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "post")
@Entity
@NoArgsConstructor
public class PostTag {

	public PostTag(int postId, int tagId) {
		this.regDate = new Date();
		this.postId = postId;
		this.tagId = tagId;
	}

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", insertable = false, updatable = false)
	private Post post;

	private int postId;

	@ManyToOne
	@JoinColumn(name = "tagId", insertable = false, updatable = false)
	private Tag tag;

	private int tagId;

	private Date regDate;
}
