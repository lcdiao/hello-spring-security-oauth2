package cn.lcdiao.spring.security.oauth2.resource.service.impl;

import cn.lcdiao.spring.security.oauth2.resource.domain.TbContent;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lcdiao.spring.security.oauth2.resource.mapper.TbContentMapper;
import cn.lcdiao.spring.security.oauth2.resource.service.TbContentService;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService{

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectAll() {
        return tbContentMapper.selectAll();
    }
}
