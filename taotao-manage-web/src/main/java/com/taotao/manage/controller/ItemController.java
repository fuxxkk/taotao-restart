package com.taotao.manage.controller;

import com.taotao.manage.service.ItemService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Item;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RequestMapping("item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQTopic itemTopicDestination;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item,@RequestParam(value="desc",required=false)String desc){
		
		try {
			Long itemId = itemService.saveItem(item, desc);
			sendMessage("save",itemId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public void updateItem(Item item,@RequestParam(value="desc",required=false)String desc) {
		try {
			itemService.updateItem(item, desc);
			sendMessage("update",item.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DatagridResult> queryItemList(@RequestParam(value="title",required=false)String title,Integer page,Integer rows){
		
		try {
			DatagridResult result = itemService.queryItemList(title, page, rows);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}

	@RequestMapping(value = "delete",method = RequestMethod.POST)
	public ResponseEntity<Void> deleteItem(Long[] ids){

		try {
			itemService.deleteByIds(ids);
			for (Long id :
					ids) {
				sendMessage("delete",id);
			}
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(500).build();
	}

	/*
	*
	 * @author Li Jin Min
	 * @Description
	 * @CreateDate 2017/12/7 11:06
	 * @Param [type, itemId]
	 * @return void
	  **/
	private void sendMessage(final String type,final Long itemId){
		try {
			jmsTemplate.send(itemTopicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {

					ActiveMQMapMessage msg = new ActiveMQMapMessage();
					msg.setString("type",type);
					msg.setLong("itemId",itemId);
				/*	Map<String,Object> map = new HashMap<>();
					map.put("ids",itemId);
					msg.setProperties(map);*/

                    return msg;
                }
            });
		} catch (JmsException e) {
			e.printStackTrace();
		}
	}
}
