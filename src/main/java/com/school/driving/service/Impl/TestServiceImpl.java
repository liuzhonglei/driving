package com.school.driving.service.Impl;

import com.school.driving.mapper.UserMapper;
import com.school.driving.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map queryUserService() {
        Map user = userMapper.testMapper();
        return user;
    }
}
