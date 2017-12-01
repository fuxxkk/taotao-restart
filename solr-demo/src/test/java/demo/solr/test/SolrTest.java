package demo.solr.test;

import demo.solr.vo.SolrItem;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class SolrTest {

    private HttpSolrServer httpSolrServer;

    @Before
    public void setup() throws Exception{
        httpSolrServer = new HttpSolrServer("http://solr.taotao.com/solr");
    }

    /**
     * 更新或创建索引
     */
    @Test
    public void testSaveOrUpdateBean() throws Exception {
        //创建提交对象
        SolrItem solrItem = new SolrItem();
        solrItem.setId(12345L);
        solrItem.setImage("http://image.taotao.com/jd/b43e6da16b414d7eb372863f502034b1.jpg");
        solrItem.setPrice(111L);
        solrItem.setSellPoint("高通骁龙820处理器，4轴防抖相机，很轻狠快！红米Note4X情人节首发，千挑万选总有你所爱！");
        solrItem.setTitle("小米5 全网通 高配版 3GB内存 64GB ROM 白色 移动联通电信4G手机");
        solrItem.setStatus(1);

        httpSolrServer.addBean(solrItem);
        httpSolrServer.commit();
    }
}
