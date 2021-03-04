package ru.shift.certbotcft.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shift.certbotcft.api.dto.AuthRequest;
import ru.shift.certbotcft.common.utils.JwtUtils;

@RestController
public class LoginController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String test(){
        return "Hello";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex){
            throw new Exception("invalid email or password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
}
