package com.mino.mdiary.service;

import com.mino.mdiary.entity.User;
import com.mino.mdiary.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {


    @Autowired
    private UserRepository userRepository;

    public void testInsert(User user) {
        userRepository.insertUser(user);
    }
}
