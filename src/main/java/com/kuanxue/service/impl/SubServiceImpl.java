package com.kuanxue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;

import com.kuanxue.service.MessageList;
import com.kuanxue.service.SubService;

public class SubServiceImpl implements SubService {

	@Autowired
	private ChannelTopic channelTopic;

	@Autowired
	private ChannelTopic channelTopic2;

	private MessageList messageList = new MessageList();

	public void onMessage(Message message, byte[] pattern) {
		System.out.println("------channelTopic-------" + channelTopic.getTopic());
		System.out.println("-----channelTopic2--------" + channelTopic2.getTopic());
		messageList.add(message.toString());
	}

	public MessageList getMessageList() {
		return messageList;
	}
}