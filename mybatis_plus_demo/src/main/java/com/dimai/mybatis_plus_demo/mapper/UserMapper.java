package com.dimai.mybatis_plus_demo.mapper;

import com.dimai.mybatis_plus_demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author z.o.s
 * @since 2019-06-20
 */
public interface UserMapper extends BaseMapper<User> {
    User queryLast();
}
