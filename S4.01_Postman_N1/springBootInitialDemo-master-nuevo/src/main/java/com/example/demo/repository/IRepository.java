package com.example.demo.repository;

import com.example.demo.dto.ResponseDto;

public interface IRepository {

    public ResponseDto getUser(String uuid);
}
