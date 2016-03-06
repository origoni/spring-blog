package com.millky.blog.domain.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.millky.blog.domain.model.entity.Tag;
import com.millky.blog.infrastructure.dao.TagDao;

@Repository
public class TagRepository {

	@Autowired
	TagDao tagDao;

	public Tag findTagByTagName(String tagName) {

		return tagDao.findByName(tagName);
	}

	public Tag createTag(Tag tag) {

		tag.setRegDate(new Date());
		return tagDao.save(tag);
	}

	public void increaseUseCount(int tagIdx) {

		Tag tag = tagDao.findOne(tagIdx);
		tag.setUpdateDate(new Date());
		tag.setUseCount(tag.getUseCount() + 1);
	}

	public void decreaseUseCount(int tagIdx) {
		Tag tag = tagDao.findOne(tagIdx);
		tag.setUpdateDate(new Date());
		tag.setUseCount(tag.getUseCount() - 1);
	}

	public Page<Tag> findAll(Pageable pageable) {

		Page<Tag> tags = tagDao.findAll(pageable);
		return tags;
	}
}
