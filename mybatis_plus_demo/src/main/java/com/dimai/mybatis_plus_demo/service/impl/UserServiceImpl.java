package com.dimai.mybatis_plus_demo.service.impl;

import com.dimai.mybatis_plus_demo.entity.User;
import com.dimai.mybatis_plus_demo.mapper.UserMapper;
import com.dimai.mybatis_plus_demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author z.o.s
 * @since 2019-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
