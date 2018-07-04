package com.mino.mdiary.controller;

import com.alibaba.fastjson.JSON;
import com.mino.mdiary.entity.User;
import com.mino.mdiary.entity.vo.ResponseInfo;
import com.mino.mdiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = JSON.parseObject(URLDecoder.decode(body, "utf-8"), User.class);
            ResponseInfo success = userService.initializeUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "register";
    }

//    @RequestMapping(value = "/register1", method = RequestMethod.POST)
//    @ResponseBody
//    public String register1(User user, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            System.out.println(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "register";
//    }
}
