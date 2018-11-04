package com.pinyougou.cas.service.impl;

import com.pinyougou.cas.dao.UserMapper;
import com.pinyougou.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查找用户
     */
    @Override
    public Map<String, Object> findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
