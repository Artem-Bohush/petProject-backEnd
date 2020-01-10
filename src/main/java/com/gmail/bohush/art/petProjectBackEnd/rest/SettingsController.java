package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.UserDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.security.JwtDecoder;
import com.gmail.bohush.art.petProjectBackEnd.security.jwt.JwtTokenProvider;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/settings")
@CrossOrigin(origins={ "http://localhost:3000"})
public class SettingsController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SettingsController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                              UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PutMapping("/setEmail")
    private ResponseEntity setNewEmail(@RequestHeader(value="authorization") String token, @RequestBody UserDto userDto) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        user.setEmail(userDto.getEmail());
        userService.save(user);
        String newToken = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        System.out.println(newToken);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", newToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkPassword")
    private ResponseEntity checkPassword(@RequestHeader(value="authorization") String token, @RequestBody UserDto userDto) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        System.out.println(userDto.getPassword());
        System.out.println(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if (bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            Map<Object, Object> response = new HashMap<>();
            response.put("isEqual", true);
            return ResponseEntity.ok(response);
        } else {
            Map<Object, Object> response = new HashMap<>();
            response.put("isEqual", false);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/setPassword")
    private HttpStatus setNewPassword(@RequestHeader(value="authorization") String token, @RequestBody UserDto userDto) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.save(user);

        return HttpStatus.OK;
    }

    @PutMapping("/setUsername")
    private HttpStatus setNewUsername(@RequestHeader(value="authorization") String token, @RequestBody UserDto userDto) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        user.setUsername(userDto.getUsername());
        userService.save(user);

        return HttpStatus.OK;
    }

    @GetMapping("/getCurrentData")
    private ResponseEntity getCurrentUserData(@RequestHeader(value="authorization") String token) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        Map<Object, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
}
