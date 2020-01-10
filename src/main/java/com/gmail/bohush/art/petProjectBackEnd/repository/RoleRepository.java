package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
