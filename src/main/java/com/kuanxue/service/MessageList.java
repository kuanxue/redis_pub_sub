package com.kuanxue.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageList {
	private static List<String> messageList = new ArrayList<String>();

	public void add(String message) {
		messageList.add(message);
	}

	public List<String> output() {
		Iterator<String> it = messageList.iterator();
		while (it.hasNext()) {
			System.out.println("messageList" + it.next());
		}
		return messageList;
	}
}
