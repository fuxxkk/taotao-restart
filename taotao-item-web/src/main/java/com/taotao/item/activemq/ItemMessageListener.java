package com.taotao.item.activemq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class ItemMessageListener extends AbstractAdaptableMessageListener{

    @Value("${taotao_item_html_path}")
    private String path;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {

    }
}
