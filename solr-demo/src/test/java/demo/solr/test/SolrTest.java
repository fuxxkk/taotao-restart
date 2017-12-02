package demo.solr.test;

import demo.solr.vo.SolrItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;


public class SolrTest {

    private HttpSolrServer httpSolrServer;

    @Before
    public void setup() throws Exception{
        httpSolrServer = new HttpSolrServer("http://192.168.12.168:8383/solr");
    }

    /**
     * 更新或创建索引
     */
    @Test
    public void testSaveOrUpdateBean() throws Exception {
        //创建提交对象
        SolrItem solrItem = null;
        List<SolrItem> list = new ArrayList<>();
        for (int i=1;i<11;i++) {
            solrItem = new SolrItem();
            solrItem.setId(12345L+i);
            solrItem.setImage("http://image.taotao.com/jd/b43e6da16b414d7eb372863f502034b1.jpg");
            solrItem.setPrice(111L);
            solrItem.setSellPoint("高通骁龙820处理器，4轴防抖相机，很轻狠快！红米Note4X情人节首发，千挑万选总有你所爱！"+i);
            solrItem.setTitle("小米5"+i+" 全网通 高配版 3GB内存 64GB ROM 白色 移动联通电信4G手机");
            solrItem.setStatus(1);
            list.add(solrItem);
        }

        httpSolrServer.addBeans(list);
        httpSolrServer.commit();
    }

    @Test
    public void testDeleteBean() throws Exception{
        httpSolrServer.deleteById("12345");
        httpSolrServer.commit();
    }

    @Test
    public void query() throws SolrServerException {
        List<SolrItem> list = doSearch("小米", 1, 10);
        System.out.println(list);
    }

    public List<SolrItem> doSearch(String keyword,int page,int rows) throws SolrServerException {

        if(!StringUtils.isNotEmpty(keyword)){
            keyword="*";
        }

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("title:"+keyword+" AND status:1");
        solrQuery.setRows(rows);
        solrQuery.setStart((page-1)*rows);

        //设置高亮
        boolean isHighLight = !keyword.equals("*");
        if(isHighLight){
            solrQuery.setHighlight(true);
            solrQuery.setHighlightSimplePre("<em>");
            solrQuery.setHighlightSimplePost("</em>");
        }

        QueryResponse query = httpSolrServer.query(solrQuery);
        List<SolrItem> list = query.getBeans(SolrItem.class);
        if(isHighLight){
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            System.out.println(highlighting);
            for (SolrItem solrItem:list) {
                solrItem.setTitle(highlighting.get(solrItem.getId().toString()).get("title").get(0));
            }

        }

        return list;
    }
}
