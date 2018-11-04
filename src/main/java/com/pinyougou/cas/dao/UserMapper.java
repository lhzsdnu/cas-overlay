package com.pinyougou.cas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过用户名查询用户角色信息
     *
     * @param userName
     */
    public Map<String, Object> findByUserName(String userName) {
        try {
            return jdbcTemplate.queryForMap("SELECT * FROM tb_user WHERE username =?", userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
