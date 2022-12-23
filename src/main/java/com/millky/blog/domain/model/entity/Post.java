package com.millky.blog.domain.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import org.springframework.beans.BeanUtils;

import com.millky.blog.domain.model.command.PostCommand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue
	int id;

	String userId;
	String name;

	@Column(nullable = false)
	String title;

	String subtitle;

	@Lob
	String content;

	Date regDate;

	Date updateDate;

	private int categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category category;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private List<PostTag> postTagList;

	public Post(PostCommand postCommand) {
		BeanUtils.copyProperties(postCommand, this);
	}
}
