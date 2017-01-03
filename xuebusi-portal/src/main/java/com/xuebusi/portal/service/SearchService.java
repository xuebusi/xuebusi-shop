package com.xuebusi.portal.service;

import com.xuebusi.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
