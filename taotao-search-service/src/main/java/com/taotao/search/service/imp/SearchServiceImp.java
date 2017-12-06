package com.taotao.search.service.imp;

import com.taotao.common.vo.DatagridResult;
import com.taotao.search.pojo.SolrItem;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImp implements SearchService {
    private static final Integer DEFAULT_PAGESIZE = 20;
    @Autowired
    private HttpSolrServer httpSolrServer;

    @Override
    public void saveOrUpdateItemList(List<SolrItem> solrItemList) throws Exception{
        httpSolrServer.addBeans(solrItemList);
        httpSolrServer.commit();
    }

    @Override
    public DatagridResult search(String keyword, Integer page) throws Exception {

        if(!StringUtils.isNoneEmpty(keyword)){
            keyword="*";
        }
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("title:"+keyword+" AND status:1");

        //设置高亮
        boolean flag = !"*".equals(keyword);
        if(flag){
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("title");
            solrQuery.setHighlightSimplePre("<em>");
            solrQuery.setHighlightSimplePost("</em>");
        }

        //设置分页
        solrQuery.setStart((page-1)*DEFAULT_PAGESIZE);
        solrQuery.setRows(DEFAULT_PAGESIZE);

        //执行查询
        QueryResponse queryResponse = httpSolrServer.query(solrQuery);
        //获取商品列表
        List<SolrItem> solrItemList = queryResponse.getBeans(SolrItem.class);
        //获取总条数
        long count = queryResponse.getResults().getNumFound();

        //设置高亮
        if(flag&&solrItemList!=null&&solrItemList.size()>0){
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            for (SolrItem item :
                    solrItemList) {
                String title = highlighting.get(item.getId().toString()).get("title").get(0);
                item.setTitle(title);
            }
        }
        /**
         "2570797": {
         "title": [
         "<em>小米</em>（MI) L43M3-AA <em>小米</em>电视3S 43英寸智能电视"
         ]
         },{},...
         */
        DatagridResult datagridResult = new DatagridResult();
        datagridResult.setRows(solrItemList);
        datagridResult.setTotal(count);
        return datagridResult;
    }
}
