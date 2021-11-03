package com.example.demo.service;

import com.example.demo.dto.UserResponseDto;

public interface IUserService {

    UserResponseDto getUser(String uuid);
}
