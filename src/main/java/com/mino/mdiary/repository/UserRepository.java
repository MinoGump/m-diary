package com.mino.mdiary.repository;

import com.mino.mdiary.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void insertUser(User user);

    User getUserByUsername(String username);
}
