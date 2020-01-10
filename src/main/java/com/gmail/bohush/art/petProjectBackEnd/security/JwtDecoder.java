package com.gmail.bohush.art.petProjectBackEnd.security;

import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import com.gmail.bohush.art.petProjectBackEnd.service.impl.UserServiceImpl;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Base64;
import java.util.Map;

public class JwtDecoder {

//    private static final UserService userService;
//
//    public JwtDecoder(UserService userService) {
//        this.userService = userService;
//    }

    public static String decodeJWT(String token){
//        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] parts = token.substring(7).split("\\.");
        String payloadJson = new String(decoder.decode(parts[1]));
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(payloadJson);
        Object mapArray[] = new Object[map.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mapArray[i] = entry.getValue();
            i++;
        }
        return (String) mapArray[0];
    }

//    public static User getUser(String token) {
//        String email = decodeJWT(token);
//        UserServiceImpl userService = new UserServiceImpl();
//        return userService.findByEmail(email);
//    }
}
