package cn.lcdiao.spring.security.oauth2.server.service;

import cn.lcdiao.spring.security.oauth2.server.domain.TbUser;

public interface TbUserService{

    TbUser getByUsername(String username);

}
