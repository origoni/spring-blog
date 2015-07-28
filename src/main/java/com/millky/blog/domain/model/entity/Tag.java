package com.millky.blog.domain.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Tag {

	public Tag(String tagName) {
		this.name = tagName;
		this.updateDate = new Date();
		this.regDate = new Date();
	}

	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String name;

	private int useCount;

	private Date updateDate;

	private Date regDate;
}
