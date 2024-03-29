package cn.lcdiao.spring.security.oauth2.server.mapper;

import cn.lcdiao.spring.security.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
    List<TbPermission> selectByUserId(@Param("id")Long id);
}