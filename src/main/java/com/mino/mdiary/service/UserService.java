package com.mino.mdiary.service;

import com.mino.mdiary.entity.User;
import com.mino.mdiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) throws Exception {
        String username = user.getUsername();
        if (username == null) {
            throw new Exception("username cannot be null");
        }
        User userInDb = userRepository.getUserByUsername(username);
        if (userInDb == null) {
            userRepository.insertUser(user);
        }
    }

    public void testInsert(User user) {
        userRepository.insertUser(user);
    }
}
