package com.mino.mdiary.repository;

import com.mino.mdiary.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public void insertUser(User user);
}
