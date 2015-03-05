package com.kuanxue;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuanxue.pojo.User;
import com.kuanxue.service.MessageList;
import com.kuanxue.service.impl.PubServiceImpl;
import com.kuanxue.service.impl.SubServiceImpl;

@Controller
public class HelloWorld {
	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Autowired
	private PubServiceImpl pubService;

	@Autowired
	private SubServiceImpl subService;

	@RequestMapping("/helloworld")
	public String hello() {
		User user = new User("1", "kuanxue", "fz");
		ValueOperations<String, User> valueops = redisTemplate.opsForValue();
		valueops.set(user.getId(), user);
		user = valueops.get("1");
		System.out.println(user.getName());
		return "success";
	}

	@RequestMapping(value = "/sub")
	public String Subscriber(Model model) {
		MessageList messageList = subService.getMessageList();
		ArrayList<String> arrayList = (ArrayList<String>) messageList.output();
		model.addAttribute("arrayList", arrayList);
		return "subResult";
	}

	@RequestMapping(value = "/pub", method = RequestMethod.GET)
	public String Subscriber() {
		return "pub";
	}

	@RequestMapping(value = "/pub", method = RequestMethod.POST)
	public String Publisher(
			@RequestParam(value = "message", required = true) String message) {
		pubService.Publisher(message);
		return "pubResult";
	}

}
