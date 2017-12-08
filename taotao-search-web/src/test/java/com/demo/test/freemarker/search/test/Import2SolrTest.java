package com.demo.test.freemarker.search.test;

import com.taotao.manage.service.ItemService;
import com.taotao.manage.pojo.Item;
import com.taotao.search.pojo.SolrItem;
import com.taotao.search.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Import2SolrTest {

    private ItemService itemService;
    private SearchService searchService;

    @Before
    public void init (){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        searchService = classPathXmlApplicationContext.getBean(SearchService.class);
        itemService = classPathXmlApplicationContext.getBean(ItemService.class);
    }

    @Test
    public void importToSolr() throws Exception {
        int page = 1;
        int pageSize = 500;
        List<Item> itemList = null;
        List<SolrItem> solrItemList = null;

        do{
            System.out.println("正在导入第"+page+"页.............");
            List<Item> items = itemService.queryListByPage( page, pageSize);
            if(items!=null&&items.size()>0){
                solrItemList = new ArrayList<>();
                for (Item i :
                        items) {
                    SolrItem solrItem = new SolrItem();
                    solrItem.setId(i.getId());
                    solrItem.setImage(i.getImage());
                    solrItem.setPrice(i.getPrice());
                    solrItem.setSellPoint(i.getSellPoint());
                    solrItem.setTitle(i.getTitle());
                    solrItem.setStatus(i.getStatus());
                    solrItemList.add(solrItem);
                }
                searchService.saveOrUpdateItemList(solrItemList);
                System.out.println("已完成第"+page+"页...................");
                page++;
                pageSize=items.size();
            }

        }while (pageSize==500);

    }
}
