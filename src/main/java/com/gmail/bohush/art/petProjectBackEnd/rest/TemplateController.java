package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.security.JwtDecoder;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/template")
@CrossOrigin(origins={ "http://localhost:3000"})
public class TemplateController {

    private final UserService userService;

    public TemplateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsername")
    private ResponseEntity getUsername(@RequestHeader(value="authorization") String token) {
        String email = JwtDecoder.decodeJWT(token);
        User user = userService.findByEmail(email);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }

}
