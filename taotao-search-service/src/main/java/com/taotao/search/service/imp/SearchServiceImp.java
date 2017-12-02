package com.taotao.search.service.imp;

import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private HttpSolrServer httpSolrServer;


}
