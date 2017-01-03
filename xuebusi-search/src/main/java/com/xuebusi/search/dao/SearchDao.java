package com.xuebusi.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.xuebusi.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
