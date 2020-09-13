package com.enjoy.controller;

import com.enjoy.session.MyRequestWrapper;
import com.enjoy.session.SessionFilter;
import com.enjoy.session.UserForm;
import com.enjoy.utils.CookieBasedSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class IndexController {

    @GetMapping("/toLogin")
    public String toLogin(Model model,MyRequestWrapper request) {
        UserForm user = new UserForm();
        user.setUsername("Peter");
        user.setPassword("Peter");
        user.setBackurl(request.getParameter("url"));
        model.addAttribute("user", user);
        System.out.println("代码3");

        return "login";
    }

    @PostMapping("/login")
    public void login(@ModelAttribute UserForm user,MyRequestWrapper request,HttpServletResponse response) throws IOException {
        System.out.println("将登陆信息传进session");
        request.getSession().setAttribute(SessionFilter.USER_INFO,user);

        CookieBasedSession.onNewSession(request,response);

        //重定向，回原url
        System.out.println("重定向回原url:" + user.getBackurl());
        response.sendRedirect(user.getBackurl());
    }

    @GetMapping("/index")
    public ModelAndView index(MyRequestWrapper request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("index");
        modelAndView.addObject("user", request.getSession().getAttribute(SessionFilter.USER_INFO));

        request.getSession().setAttribute("test","123");
        return modelAndView;
    }
}
