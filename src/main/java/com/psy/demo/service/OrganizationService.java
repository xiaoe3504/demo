package com.psy.demo.service;


import com.psy.demo.dto.OrganizationDTO;
import com.psy.demo.vo.res.OrganizationVO;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public interface OrganizationService {

    @SneakyThrows
    List<OrganizationVO> getMapOrg();

    int dealAdd(OrganizationDTO dto);

}
