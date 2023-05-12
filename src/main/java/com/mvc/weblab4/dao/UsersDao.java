package com.mvc.weblab4.dao;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsersDao {

  private static final Map<String, String> users = new HashMap<>();

  static {
    users.put("admin", "admin");
    users.put("user", "user");
  }

  public boolean isExist(String login, String password) {
    return users.containsKey(login) && users.get(login).equals(password);
  }

  public Map<String, String> findAll() {
    return users;
  }
}
