package com.millky.blog.domain.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Category {

	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String name;

	private Date regDate;

	private int postCount;

	private int publicPostCount;
}
