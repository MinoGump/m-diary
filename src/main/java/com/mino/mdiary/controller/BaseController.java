package com.mino.mdiary.controller;

import com.mino.mdiary.entity.User;
import com.mino.mdiary.entity.vo.ResponseInfo;
import com.mino.mdiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"user"})
public class BaseController {

    @Autowired
    private UserService userService;

    /*
     Without this, user can set any Foo fields they want with a custom HTTP POST
     setAllowedFields disallows all other fields.
     You don't even need setters for id and version, as Hibernate sets them using reflection
    */
    @InitBinder
    void allowFields(WebDataBinder webDataBinder){
        webDataBinder.setAllowedFields("username", "password");
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody String register(@ModelAttribute("user") User user1,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes,
                                               HttpServletRequest request,
                                               SessionStatus sessionStatus) {
        try {
            User user = new User();
//            User user = JSON.parseObject(URLDecoder.decode(body, "utf-8"), User.class);
            ResponseInfo success = userService.initializeUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseInfo responseInfo = new ResponseInfo(true, 100, "success", "data...");
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
