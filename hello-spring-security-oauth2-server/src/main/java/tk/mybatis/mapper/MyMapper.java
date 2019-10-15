package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author diao
 * @date 2019/10/15
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
