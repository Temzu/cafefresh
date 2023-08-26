package com.temzu.cafefresh.daos;


import com.temzu.cafefresh.entities.User;

public interface UserDao {

  User save(User user);

  User findByLogin(String login);

  User findByEmail(String email);

  User findByEmailAndPassword(String email, String password);

  User update(User user);

  void updatePass(String newPass, String currentUser);
}
