package com.millky.blog.domain.service.factory;

import com.millky.blog.domain.service.PostSearchService;

public interface SearchServiceFactory {

	PostSearchService getSearchService(String selector);
}
