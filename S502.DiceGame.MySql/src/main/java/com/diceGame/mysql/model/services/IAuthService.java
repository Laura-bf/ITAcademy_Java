package com.diceGame.mysql.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diceGame.mysql.model.DTO.LoginResponse;
import com.diceGame.mysql.model.DTO.PlayerDTO;
import com.diceGame.mysql.model.DTO.SignUpRequest;

@Service("auth")
public interface IAuthService {
    LoginResponse login(String username, String password);

    PlayerDTO signUp(SignUpRequest request);

    void removeUser(String username);

//    UserResponse searchUser(String userName);
//    List<PlayerDTO> getAllUser();

    String refreshToken(String userName);

}
