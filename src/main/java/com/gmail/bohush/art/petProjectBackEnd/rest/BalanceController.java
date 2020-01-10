package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.UserDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.security.JwtDecoder;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/balance")
@CrossOrigin(origins={ "http://localhost:3000"})
public class BalanceController {

    private final UserService userService;

    public BalanceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getCurrent")
    private ResponseEntity getCurrentBalance(@RequestHeader(value="authorization") String token) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        Map<Object, Object> response = new HashMap<>();
        response.put("balance", user.getBalance());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/setBalance")
    private HttpStatus setNewBalance(@RequestHeader(value="authorization") String token, @RequestBody UserDto userDto) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        user.setBalance(userDto.getBalance());
        userService.save(user);

        return HttpStatus.OK;
    }
}
