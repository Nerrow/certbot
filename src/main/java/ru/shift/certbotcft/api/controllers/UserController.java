package ru.shift.certbotcft.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shift.certbotcft.api.common.UrlPath;
import ru.shift.certbotcft.api.dto.User;
import ru.shift.certbotcft.api.services.UserService;

import java.awt.*;

@RestController
@RequestMapping(value = UrlPath.USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@ResponseBody User user){
        userService.add(user);
    }
}
