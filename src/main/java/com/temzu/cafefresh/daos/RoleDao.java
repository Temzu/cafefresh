package com.temzu.cafefresh.daos;


import com.temzu.cafefresh.entities.Role;

public interface RoleDao {

  Role findByName(String name);
}
