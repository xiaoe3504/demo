package com.psy.demo.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.psy.demo.dto.OrganizationDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.OrganizationMapper;
import com.psy.demo.service.OrganizationService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.res.OrganizationVO;
import com.psy.demo.vo.res.WeChatCerRes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.psy.demo.utils.MyConstantString.ORGANIZATION;


@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    private final LoadingCache<String, List<OrganizationVO>> cacheOrg = CacheBuilder.newBuilder()
            .maximumSize(10) // 最多缓存100个键值对
            .expireAfterWrite(5, TimeUnit.MINUTES) // 写入后5分钟过期
            .build(
                    new CacheLoader<String, List<OrganizationVO>>() {
                        @NotNull
                        public List<OrganizationVO> load(@NotNull String key) {
                            if (key.equals(ORGANIZATION)) {
                                return organizationMapper.queryAll().stream().map(OrganizationDTO::genVO).collect(Collectors.toList());
                            }
                            return new ArrayList<>();
                        }
                    }
            );

    @PostConstruct
    public void init() {
        try {
            cacheOrg.get(ORGANIZATION);
        } catch (ExecutionException e) {
            log.error("userInfo get cache error:" + e.getMessage(), e);
            throw new BaseException("userInfo get cache error");
        }
    }

    @SneakyThrows
    @Override
    public List<OrganizationVO> getMapOrg() {
        return cacheOrg.get(ORGANIZATION);
    }


    @Override
    public int dealAdd(OrganizationDTO dto) {
        return organizationMapper.insert(dto);
    }

}
