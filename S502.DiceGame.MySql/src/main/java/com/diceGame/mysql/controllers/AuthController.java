package com.diceGame.mysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.diceGame.mysql.model.DTO.LoginRequest;
import com.diceGame.mysql.model.DTO.LoginResponse;
import com.diceGame.mysql.model.DTO.PlayerDTO;
import com.diceGame.mysql.model.DTO.SignUpRequest;
import com.diceGame.mysql.model.services.IAuthService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AuthController {

	@Autowired
    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/public/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest requestHeader, @RequestBody LoginRequest request) throws RuntimeException {

        LoginResponse loginResponse = authService.login(request.getUserName(), request.getPassword());
        if(loginResponse == null){
            throw new RuntimeException("Login failed. Possible cause : incorrect username/password");
        }else{
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/public/signUp")
    public ResponseEntity<PlayerDTO> signUp(HttpServletRequest requestHeader, @RequestBody SignUpRequest request) throws RuntimeException {

        PlayerDTO user;
        try {
            user = authService.signUp(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestParam String userName) throws RuntimeException {
        try {
            authService.removeUser(userName);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

//    @GetMapping(value = "getAllUser")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<List<User>> getAllUser() throws RuntimeException {
//        try {
//            return new ResponseEntity<>(authService.getAllUser(), HttpStatus.OK);
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }

//    @GetMapping(value = "searchUser")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//    public ResponseEntity<UserResponse> searchUser(@RequestParam String userName) throws RuntimeException {
//
//        UserResponse userResponse = authService.searchUser(userName);
//        return new ResponseEntity<>(userResponse, HttpStatus.OK);
//    }

    @GetMapping("/refreshToken")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refreshToken(HttpServletRequest req) {
        return authService.refreshToken(req.getRemoteUser());
    }

}
