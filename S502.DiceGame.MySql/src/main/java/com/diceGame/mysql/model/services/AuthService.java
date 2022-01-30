package com.diceGame.mysql.model.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diceGame.mysql.model.domain.Player;
import com.diceGame.mysql.model.DTO.LoginResponse;
import com.diceGame.mysql.model.DTO.PlayerDTO;
import com.diceGame.mysql.model.DTO.SignUpRequest;
import com.diceGame.mysql.model.persistance.PlayerMysqlRepository;
import com.diceGame.mysql.security.IJwtTokenProviderService;
import com.diceGame.utils.UserException;

import java.util.List;


@Service("auth")
public class AuthService  extends PlayerMysqlServiceImpl implements IAuthService {
    private static Log log = LogFactory.getLog(AuthService.class);

    private PasswordEncoder passwordEncoder;

    private IJwtTokenProviderService jwtTokenProviderService;
    private AuthenticationManager authenticationManager;


    public AuthService(PlayerMysqlRepository playerRepository, PasswordEncoder passwordEncoder, IJwtTokenProviderService jwtTokenProviderService, AuthenticationManager authenticationManager) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProviderService = jwtTokenProviderService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(String userName, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            Player user = playerRepository.findByName(userName);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserName(user.getName());
            loginResponse.setAccessToken(jwtTokenProviderService.createToken(userName, user.getRoles()));

            log.info("Login successfully");

            return loginResponse;
        } catch (AuthenticationException e) {
            throw new UserException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public PlayerDTO signUp(SignUpRequest request) {
        if(playerRepository.findByName(request.getUserName())!=null){
            throw new UserException("User already exists in system", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Player user = new Player();
        user.setName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        request.setPassword(user.getPassword());

        playerRepository.save(user);
        log.info("Register successfully");

        return mapEntityToDto(user);
    }

    @Override
    public void removeUser(String userName) {
    	 if(playerRepository.findByName(userName)==null){
            throw new RuntimeException("User doesn't exists");
        }
        playerRepository.deleteByName(userName);
        log.info("User remove successfully");

    }

//    @Override
//    public UserResponse searchUser(String userName) {
//        User user = playerRepository.findByUsername(userName);
//        if (user == null) {
//            throw new MyCustomException("Provided user doesn't exist", HttpStatus.NOT_FOUND);
//        }
//
//        UserResponse userResponse = new UserResponse();
//        userResponse.setEmail(user.getEmail());
//        userResponse.setUserName(user.getUsername());
//
//        return userResponse;
//    }

//    @Override
//    public List<User> getAllUser() {
//        return playerRepository.findAll();
//    }

    @Override
    public String refreshToken(String userName) {
        return jwtTokenProviderService.createToken(userName, playerRepository.findByName(userName).getRoles());
    }
}
