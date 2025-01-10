package com.psy.demo.service;


import com.psy.demo.dto.OrganizationDTO;
import lombok.SneakyThrows;

import java.util.Map;

public interface OrganizationService {

    @SneakyThrows
    Map<String,String> getMapOrg();

    int dealAdd(OrganizationDTO dto);

}
