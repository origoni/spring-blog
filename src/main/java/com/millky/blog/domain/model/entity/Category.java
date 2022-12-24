package com.millky.blog.domain.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
