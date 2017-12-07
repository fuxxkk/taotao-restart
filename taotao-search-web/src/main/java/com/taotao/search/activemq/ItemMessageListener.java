package com.taotao.search.activemq;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import com.taotao.search.pojo.SolrItem;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class ItemMessageListener extends AbstractAdaptableMessageListener{

    @Autowired
    private ItemService itemService;
    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message, Session session)  {
        try {
            if (message instanceof MapMessage){
                MapMessage msg = (MapMessage) message;
                String type = msg.getString("type");
                long itemId = msg.getLong("itemId");

                if ("delete".equals(type)){
                    searchService.deleteItem(itemId);
                }else {
                    Item item = itemService.queryById(itemId);
                    SolrItem solrItem = new SolrItem();
                    solrItem.setTitle(item.getTitle());
                    solrItem.setId(item.getId());
                    solrItem.setImage(item.getImage());
                    solrItem.setPrice(item.getPrice());
                    solrItem.setSellPoint(item.getSellPoint());
                    solrItem.setStatus(item.getStatus());
                    searchService.saveOrUpdateItem(solrItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
