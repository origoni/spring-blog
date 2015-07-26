package com.millky.blog.domain.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.entity.PostTag;
import com.millky.blog.domain.model.entity.Tag;
import com.millky.blog.domain.repository.PostRepository;
import com.millky.blog.domain.repository.PostTagRepository;
import com.millky.blog.domain.repository.TagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private PostTagRepository postTagRepository;

	public Post writePost(Post post, UserSession user) {

		post.setUserId(user.getProviderUserId());
		post.setName(user.getDisplayName());

		Post npost = postRepository.writePost(post);

		HashSet<String> hashSet = new HashSet<>(
				Arrays.asList(post.getTags().trim().replaceAll("(\\p{Space}){2,}", "").split(" ", 10)));

		log.debug("hashSet = {}", hashSet);

		for (String tagName : hashSet) {
			if (tagName.equals("")) {
				continue;
			}

			Tag tag = tagRepository.findTagByTagName(tagName);
			if (tag == null) {
				tag = tagRepository.createTag(new Tag(tagName));
			}

			postTagRepository.insertPostTag(new PostTag(npost.getId(), tag.getId()));
		}

		return npost;
	}
}
