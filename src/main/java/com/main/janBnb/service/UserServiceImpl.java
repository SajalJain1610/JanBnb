package com.main.janBnb.service;

import com.main.janBnb.entity.User;
import com.main.janBnb.exception.UserAlreadyExistsException;
import com.main.janBnb.payload.UserDto;
import com.main.janBnb.payload.loginDto;
import com.main.janBnb.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public UserDto signUpUser(UserDto dto) {
         Optional<User>  opUser = userRepository.findByUsername(dto.getUsername());
         if(opUser.isPresent()){
             throw new UserAlreadyExistsException("user is already exists");
         }
        User user = userDtoToEntity(dto);
         User savedUser = userRepository.save(user);
         UserDto userDto = userEntityToDto(savedUser);
        return userDto;
    }

    @Override
    public String loginUser(loginDto dto) {
      User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(
                 ()-> new  UsernameNotFoundException("user not found")
         );
      if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){
             String token = jwtService.generateToken(user);
             return token;
      }
      return null;

    }
    private UserDto userEntityToDto(User savedUser) {
        UserDto user = new UserDto();
        user.setUserId(savedUser.getUserId());
        user.setName(savedUser.getName());
        user.setEmail(savedUser.getEmail());
        user.setUsername(savedUser.getUsername());
        user.setPassword(savedUser.getPassword());
        user.setRole(savedUser.getRole());
        return user;
    }

    private User userDtoToEntity(UserDto dto) {
        User user = new User();
         String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        user.setRole(dto.getRole());
        return user;
    }
}
