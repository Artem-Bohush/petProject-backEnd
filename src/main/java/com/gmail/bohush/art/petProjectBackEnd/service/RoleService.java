package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.Role;

public interface RoleService {

    void save(Role role);

    Role findByName(String name);
}
