package com.kuanxue.service;

import com.kuanxue.pojo.User;

public interface UserDao {
	void save(User user);

	User read(String id);

	void delete(String id);
}
