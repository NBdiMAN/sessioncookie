package com.enjoy.controller;

import com.enjoy.session.MyRequestWrapper;
import com.enjoy.session.SessionFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/")
public class TestController{
//    @ApiOperation(value="取帐户信息")
//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public String getSession(MyRequestWrapper request, HttpServletResponse response){
//        HttpSession httpSession = request.getSession();
//        CookieBasedSession.onNewSession(request,response);
//        return  (String) httpSession.getAttribute("account");
//    }

    @GetMapping("/index")
    public ModelAndView index(MyRequestWrapper request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("index");
        modelAndView.addObject("user", request.getSession().getAttribute(SessionFilter.USER_INFO));

        return modelAndView;
    }

}
