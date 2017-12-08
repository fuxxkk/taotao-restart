package com.taotao.item.activemq;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemMessageListener extends AbstractAdaptableMessageListener{

    @Value("${taotao_item_html_path}")
    private String path;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
            if(message instanceof MapMessage){
                ActiveMQMapMessage msg = (ActiveMQMapMessage) message;
                String type = msg.getString("type");
                long itemId = msg.getLong("itemId");

                Item item = itemService.queryById(itemId);
                ItemDesc itemDesc = itemDescService.queryById(itemId);

                try {
                    if (!"delete".equalsIgnoreCase(type)) {
                        Configuration configuration = freeMarkerConfigurer.getConfiguration();
                        Template template = configuration.getTemplate("item.ftl");
                        Map<String, Object> map = new HashMap<>();
                        map.put("item",item);
                        map.put("itemDesc", itemDesc);
                        FileWriter fileWriter = new FileWriter(path + File.separator + itemId + ".html");
                        template.process(map,fileWriter);
                    }else {
                        File file = new File(path + File.separator + itemId + ".html");
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
