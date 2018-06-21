package com.mino.mdiary.service;

import com.mino.mdiary.entity.User;
import com.mino.mdiary.entity.vo.ResponseInfo;
import com.mino.mdiary.enums.LogType;
import com.mino.mdiary.repository.UserRepository;
import com.mino.mdiary.util.LogUtil;
import com.mino.mdiary.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static com.mino.mdiary.common.Constants.USER_INIT_FAIL_CODE;

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

    @Transactional
    public ResponseInfo initializeUser(User user) {
        try {
            if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsername())) {
                return ResponseUtil.getFailedResponse(USER_INIT_FAIL_CODE, "用户名或密码为空");
            }
            User dbUser = userRepository.getUserByUsername(user.getUsername());
            if (dbUser != null) {
                return ResponseUtil.getFailedResponse(USER_INIT_FAIL_CODE, "用户名已被注册");
            }
            User defaultUser = new User();
            defaultUser.setAvatar("");
            defaultUser.setUsername(user.getUsername());
            defaultUser.setPassword(user.getPassword());
            defaultUser.setNickname("");
            defaultUser.setCreateTime(Calendar.getInstance().getTime());
            defaultUser.setIsDeleted((byte) 0);
            userRepository.insertUser(defaultUser);
            return ResponseUtil.getSuccessfulResponse("成功", "");
        } catch (Exception e) {
            LogUtil.logEvent(UserService.class, LogType.ERROR, "initializeUser Error!, message : %s", e.getMessage());
        }
        return ResponseUtil.getFailedResponse(USER_INIT_FAIL_CODE, "系统异常");
    }

    public void testInsert(User user) {
        userRepository.insertUser(user);
    }
}
