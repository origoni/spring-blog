package com.millky.blog.domain.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Post {

	@Id
	@GeneratedValue
	int id;

	String userId;
	String name;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(nullable = false)
	String title;

	@Size(max = 255)
	String subtitle;

	@NotNull
	@Size(min = 1, max = 100000000)
	@Column(length = 100000000)
	String content;

	String _csrf;

	Date regDate;

	@Min(value = 1)
	private int categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category category;

	String tags;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PostTag> tagList;
}
