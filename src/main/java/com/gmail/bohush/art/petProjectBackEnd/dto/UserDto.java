package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String balance;
    private String status;
    //    private List<Role> roles;
    private List<Record> records;

//    public User toUser() {
//        User user = new User();
//        user.setId(id);
//    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setBalance(user.getBalance());
        userDto.setStatus(user.getStatus().name());
//        adminUserDto.setRoles(user.getRoles());
        userDto.setRecords(user.getRecords());
        return userDto;
    }
}
