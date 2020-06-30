package com.hdd.logintest.mapper;

import com.hdd.logintest.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 用户登录验证
     * @param user
     * @return
     */
     User login(User user);


    int registerUser(User user);
}
