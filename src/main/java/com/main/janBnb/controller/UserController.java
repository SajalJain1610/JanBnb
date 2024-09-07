package com.main.janBnb.controller;

import com.main.janBnb.payload.JwtResponse;
import com.main.janBnb.payload.UserDto;
import com.main.janBnb.payload.loginDto;
import com.main.janBnb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/main/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserDto dto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
         UserDto userDto = userService.signUpUser(dto);
         return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody loginDto dto){
         String token = userService.loginUser(dto);
         if(token!=null){
             JwtResponse jwtResponse = new JwtResponse();
             jwtResponse.setToken(token);
             jwtResponse.setType("JWT TOKEN");
             return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
         }
       return new ResponseEntity<>("invalid password",HttpStatus.BAD_REQUEST);
    }

}
