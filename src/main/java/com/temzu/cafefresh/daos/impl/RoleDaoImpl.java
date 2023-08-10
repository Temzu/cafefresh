package com.temzu.cafefresh.daos.impl;

import com.temzu.freshcafe.dao.RoleDao;
import com.temzu.freshcafe.entities.Role;
import com.temzu.freshcafe.exceptions.ResourceNotFoundException;
import com.temzu.freshcafe.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {

  private final RoleRepository roleRepository;

  @Override
  public Role findByName(String name) {
    return roleRepository
        .findByName(name)
        .orElseThrow(() -> ResourceNotFoundException.byName(name, Role.class));
  }
}
