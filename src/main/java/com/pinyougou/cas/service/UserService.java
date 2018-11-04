package com.pinyougou.cas.service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     */
    Map<String,Object> findByUserName(String userName);

}
