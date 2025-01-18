package com.psy.demo.service;


import com.psy.demo.dto.AccessTokenDTO;
import lombok.SneakyThrows;


public interface AccessTokenService {

    @SneakyThrows
    String getCozeAccessToken();

    int insertOrUpdate(AccessTokenDTO dto);
}
