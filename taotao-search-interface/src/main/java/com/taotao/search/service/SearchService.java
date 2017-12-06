package com.taotao.search.service;

import com.taotao.search.pojo.SolrItem;

import java.util.List;

public interface SearchService {

    void saveOrUpdateItemList(List<SolrItem> solrItemList) throws Exception;
}
