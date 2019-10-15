package cn.lcdiao.spring.security.oauth2.server.service;

import cn.lcdiao.spring.security.oauth2.server.domain.TbPermission;

import java.util.List;

public interface TbPermissionService{

    List<TbPermission> selectByUserId(Long id);
}
