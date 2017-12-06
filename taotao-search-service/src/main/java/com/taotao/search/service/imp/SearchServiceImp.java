package com.taotao.search.service.imp;

import com.taotao.search.pojo.SolrItem;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private HttpSolrServer httpSolrServer;

    @Override
    public void saveOrUpdateItemList(List<SolrItem> solrItemList) throws Exception{
        httpSolrServer.addBeans(solrItemList);
        httpSolrServer.commit();
    }
}
