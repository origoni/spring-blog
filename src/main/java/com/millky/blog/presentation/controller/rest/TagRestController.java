package com.millky.blog.presentation.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.millky.blog.domain.model.entity.Tag;
import com.millky.blog.domain.repository.TagRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@RestController
@RequestMapping(value = { "/api/v1" }, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Tag API")
public class TagRestController {

	@Autowired
	private TagRepository tagRepository;

	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	public List<Tag> tags(@PageableDefault(sort = { "updateDate" }, direction = Direction.DESC) Pageable pageable) {

		return tagRepository.findAll(pageable).getContent();
	}

	@ApiOperation(value = "태그 리스트 for Cloud", notes = "태그 클라우드에서 사용할 태그 리스트를 가지고 옵니다.")
	@RequestMapping(value = "/tag-cloud", method = RequestMethod.GET)
	public List<TagCloud> tagCloud(@ModelAttribute @Valid TagCloudCommand command) {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updateDate"));
		Pageable pageable = new PageRequest(0, command.getSize(), sort);
		return tagRepository.findAll(pageable).getContent().stream().map(TagCloud::new).collect(Collectors.toList());
	}

	@Data
	static class TagCloudCommand {
		@Max(value = 100)
		@ApiParam(required = false, value = "한번에 가지고 올 태그의 수<br>(기본값 : 20, 최대 : 100)")
		int size = 20;
	}

	@Data
	@ApiModel(value = "TagCloud")
	static class TagCloud {
		TagCloud(Tag tag) {
			text = tag.getName();
			weight = tag.getUseCount();
			link = "/tag/" + tag.getName() + "/post/list";
		}

		@ApiModelProperty(value = "태그명")
		String text;

		@ApiModelProperty(value = "태그의 사용 개수")
		int weight;

		@ApiModelProperty(value = "태그를 눌렀을때 이동할 링")
		String link;
	}
}
