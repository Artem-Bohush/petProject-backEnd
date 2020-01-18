package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.entity.Role;
import com.gmail.bohush.art.petProjectBackEnd.entity.Status;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String email;
    private String password;
    private String username;
    private double balance;
    private List<Role> roles;
    private List<Record> records;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setBalance(user.getBalance());
//        adminUserDto.setRoles(user.getRoles());
        userDto.setRecords(user.getRecords());
        return userDto;
    }
}
