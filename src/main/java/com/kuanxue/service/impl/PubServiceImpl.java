package com.kuanxue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.kuanxue.service.PubService;

@Service
public class PubServiceImpl implements PubService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ChannelTopic channelTopic;

	@Autowired
	private ChannelTopic channelTopic2;

	public void Publisher(String message) {
		stringRedisTemplate.convertAndSend(channelTopic.getTopic(), message);

		stringRedisTemplate.convertAndSend(channelTopic2.getTopic(), message + "channelTopic2");
	}
}