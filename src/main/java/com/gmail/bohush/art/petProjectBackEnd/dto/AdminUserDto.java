package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.entity.Role;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private double balance;
    private String status;
//    private List<Role> roles;
    private List<Record> records;

//    public User toUser() {
//        User user = new User();
//        user.setId(id);
//    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setPassword(user.getPassword());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setBalance(user.getBalance());
        adminUserDto.setStatus(user.getStatus().name());
//        adminUserDto.setRoles(user.getRoles());
        adminUserDto.setRecords(user.getRecords());
        return adminUserDto;
    }
}
