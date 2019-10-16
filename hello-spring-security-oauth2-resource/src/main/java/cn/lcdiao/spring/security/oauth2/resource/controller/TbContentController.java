package cn.lcdiao.spring.security.oauth2.resource.controller;

import cn.lcdiao.spring.security.oauth2.resource.domain.TbContent;
import cn.lcdiao.spring.security.oauth2.resource.dto.ResponseResult;
import cn.lcdiao.spring.security.oauth2.resource.service.TbContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author diao
 * @date 2019/10/16
 */
@RestController
public class TbContentController {

    @Resource
    private TbContentService tbContentService;


    @GetMapping(value = "/")
    public ResponseResult<List<TbContent>> list() {
        List<TbContent> tbContents = tbContentService.selectAll();
        System.out.println(tbContents);
        return new ResponseResult<List<TbContent>>(HttpStatus.OK.value(),HttpStatus.OK.toString(),tbContents);
    }

}
