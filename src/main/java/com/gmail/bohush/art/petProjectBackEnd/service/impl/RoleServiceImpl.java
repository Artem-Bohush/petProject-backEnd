package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.Role;
import com.gmail.bohush.art.petProjectBackEnd.repository.RoleRepository;
import com.gmail.bohush.art.petProjectBackEnd.repository.UserRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.RoleService;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
