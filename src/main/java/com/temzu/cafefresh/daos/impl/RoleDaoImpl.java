package com.temzu.cafefresh.daos.impl;

import com.temzu.cafefresh.daos.RoleDao;
import com.temzu.cafefresh.entities.Role;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.repositories.RoleRepository;
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
