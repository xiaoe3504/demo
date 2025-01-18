package com.psy.demo.service.impl;

import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.OrganizationService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.req.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    OrganizationService organizationService;

    @Override
    public int dealAdd(String openId) {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().openId(openId).build();
        if (adjustUserExist(openId)) {
            return -1;
        }
        int res;
        try {
            res = userInfoMapper.insert(userInfoDTO);
        } catch (Exception e) {
            log.error("insert error:" + e.getMessage(), e);
            throw new BaseException("insert error: " + e.getMessage());
        }

        return res;
    }

    private boolean adjustUserExist(String openId) {
        UserInfoDTO dtoDB = userInfoMapper.getDTOByOpenId(openId);
        if (dtoDB != null) {
            log.info("db exist... openId:" + openId);
            return true;
        }
        return false;
    }

    @Override
    public int saveOrUpdateNickname(UserInfoDTO userInfoDTO) {
        adjustParams(userInfoDTO);
        return userInfoMapper.insertOrUpdate(userInfoDTO);
    }

    @Override
    public UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO) {
        String openId = userInfoDTO.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        return userInfoMapper.getDTOByOpenId(openId);
    }

    @Override
    public int updateIsMemberByOpenId(String openId) {
        return userInfoMapper.updateIsMemberByOpenId(openId);
    }

    @Override
    public boolean adjustIsMember(String openId) {
        UserInfoDTO dto = userInfoMapper.getDTOByOpenId(openId);
        return dto != null && dto.getIsMember() == 1;
    }

    @Override
    public int dealAddNotMemberMsgCnt(String openId) {
        userInfoMapper.updateNotMemberMsgCnt(openId);
        UserInfoDTO dto = userInfoMapper.getDTOByOpenId(openId);
        return dto.getNotMemberMsgCnt();
    }

    @Override
    public int saveOrUpdateUserInfoReg(UserInfoVO vo) {
        adjustParamsReg(vo);
        return userInfoMapper.insertOrUpdateAvatarPhoneNickRealOrg(vo);
    }

    @Override
    public boolean getHasReg(String openId) {
        UserInfoDTO dto = userInfoMapper.selectDTOByOpenId(openId);
        if (dto == null) {
            return false;
        }
        Long organizationId = dto.getOrganizationId();
        return organizationId !=null && organizationId > 0;
    }

    private void adjustParamsReg(UserInfoVO vo) {
        if (StringUtils.isEmpty(vo.getNickName())) {
            throw new BaseException("nickname can not be null");
        }
        if (StringUtils.isEmpty(vo.getOpenId())) {
            throw new BaseException("openId can not be null");
        }
        if (StringUtils.isEmpty(vo.getPhoneNumber())) {
            throw new BaseException("phoneNumber can not be null");
        }

        if (StringUtils.isEmpty(vo.getAvatarUrl())) {
            throw new BaseException("avatarUrl can not be null");
        }
        if (StringUtils.isEmpty(vo.getRealName())) {
            throw new BaseException("realName can not be null");
        }
        if (StringUtils.isEmpty(vo.getOrganizationName())) {
            throw new BaseException("organizationName can not be null");
        }

        String organizationId = organizationService.getMapOrg()
                .getOrDefault(vo.getOrganizationName(), "");
        if (StringUtils.isEmpty(organizationId)) {
            throw new BaseException("organizationId not match");
        }
        vo.setOrganizationId(Long.valueOf(organizationId));
    }

    private void adjustParams(UserInfoDTO userInfoDTO) {
        if (StringUtils.isEmpty(userInfoDTO.getNickName())) {
            throw new BaseException("nickname can not be null");
        }
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())) {
            throw new BaseException("openId can not be null");
        }
        if (StringUtils.isEmpty(userInfoDTO.getPhoneNumber())) {
            throw new BaseException("phoneNumber can not be null");
        }
    }


}
