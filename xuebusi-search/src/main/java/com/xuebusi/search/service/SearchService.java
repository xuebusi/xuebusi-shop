package com.xuebusi.search.service;

import com.xuebusi.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
