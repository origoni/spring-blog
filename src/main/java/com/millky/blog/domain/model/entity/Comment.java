package com.millky.blog.domain.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
// @JsonRootName(value = "comment")
public class Comment {
	@Id
	@GeneratedValue
	int id;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "postId", insertable = false, updatable = false)
	// Post post;
	int postId;

	String userId;
	String name;

	String content;

	Date regDate;
}
