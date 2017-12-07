package com.taotao.search.service;

import com.taotao.common.vo.DatagridResult;
import com.taotao.search.pojo.SolrItem;

import java.util.List;

public interface SearchService {

    void saveOrUpdateItemList(List<SolrItem> solrItemList) throws Exception;

    void saveOrUpdateItem(SolrItem solrItem) throws Exception;

    DatagridResult search(String keyword,Integer page) throws Exception;

    void deleteItem(Long id)throws Exception;
}
