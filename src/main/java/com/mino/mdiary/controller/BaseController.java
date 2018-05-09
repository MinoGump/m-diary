package com.mino.mdiary.controller;

import com.mino.mdiary.entity.User;
import com.mino.mdiary.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
public class BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User user) {
        testService.testInsert(user);
        return "register";
    }
}
