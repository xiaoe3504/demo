package com.psy.demo.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.psy.demo.dto.OrganizationDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.OrganizationMapper;
import com.psy.demo.service.OrganizationService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.res.WeChatCerRes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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

    private final LoadingCache<String, Map<String, String>> cacheOrg = CacheBuilder.newBuilder()
            .maximumSize(10) // 最多缓存100个键值对
            .expireAfterWrite(2, TimeUnit.DAYS) // 写入后2天过期
            .build(
                    new CacheLoader<String, Map<String, String>>() {
                        @NotNull
                        public Map<String, String> load(@NotNull String key) {
                            if (key.equals(ORGANIZATION)) {
                                return organizationMapper.queryAll().stream().collect(Collectors.toMap
                                        (OrganizationDTO::getName, dto -> String.valueOf(dto.getId())));
                            }
                            return new HashMap<>();
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
    public Map<String, String> getMapOrg() {
        return cacheOrg.get(ORGANIZATION);
    }


    @Override
    public int dealAdd(OrganizationDTO dto) {
        return organizationMapper.insert(dto);
    }

}
