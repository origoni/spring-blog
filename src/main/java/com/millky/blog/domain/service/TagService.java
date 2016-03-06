package com.millky.blog.domain.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.millky.blog.domain.model.command.PostCommand;
import com.millky.blog.domain.model.entity.PostTag;
import com.millky.blog.domain.model.entity.Tag;
import com.millky.blog.domain.repository.PostTagRepository;
import com.millky.blog.domain.repository.TagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private PostTagRepository postTagRepository;

	public void insertPostTag(PostCommand postCommand) {

		HashSet<String> hashSet = tagNamesToHashSet(postCommand.getTags());

		log.debug("hashSet = {}", hashSet);

		insertTag(postCommand.getId(), hashSet);
	}

	public void updatePostTag(PostCommand postCommand) {

		List<PostTag> oldPostTagList = postTagRepository.findByPostId(postCommand.getId());
		HashSet<String> newTagHashSet = tagNamesToHashSet(postCommand.getTags());

		Iterator<String> newTagIterator = newTagHashSet.iterator();
		while (newTagIterator.hasNext()) {
			String newTag = newTagIterator.next();
			Iterator<PostTag> oldTagIterator = oldPostTagList.iterator();
			while (oldTagIterator.hasNext()) {
				String oldTag = oldTagIterator.next().getTag().getName();
				if (newTag.equals(oldTag)) {
					newTagIterator.remove();
					oldTagIterator.remove();
				}
			}
		}

		if (newTagHashSet != null) {
			insertTag(postCommand.getId(), newTagHashSet);
		}
		if (oldPostTagList != null) {
			deleteTag(oldPostTagList);
		}

	}

	public void deletePostTagByPostId(int postId) {
		List<PostTag> postTagList = postTagRepository.findByPostId(postId);

		deleteTag(postTagList);
	}

	private HashSet<String> tagNamesToHashSet(String postTagNames) {

		StringTokenizer tokenTag = new StringTokenizer(postTagNames.trim().replaceAll("[ ]+", " "), " ");

		HashSet<String> hashSet = new HashSet<>();

		while (tokenTag.hasMoreElements()) {
			hashSet.add(tokenTag.nextToken());
			if (hashSet.size() >= 10) {
				break;
			}
		}

		return hashSet;
	}

	private void insertTag(int postId, HashSet<String> hashSet) {
		for (String tagName : hashSet) {
			if (tagName.equals("")) {
				continue;
			}

			Tag tag = tagRepository.findTagByTagName(tagName);
			if (tag == null) {
				tag = tagRepository.createTag(new Tag(tagName));
			}

			postTagRepository.insertPostTag(new PostTag(postId, tag.getId()));
		}
	}

	private void deleteTag(List<PostTag> postTagList) {
		for (PostTag postTag : postTagList) {
			this.postTagRepository.deletePostTag(postTag);
		}
	}
}
