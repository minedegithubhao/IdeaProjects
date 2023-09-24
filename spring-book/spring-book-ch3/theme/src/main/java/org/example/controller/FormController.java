package org.example.controller;

import org.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * User: mertcaliskan
 * Date: 16/06/14
 */
@Controller
public class FormController {

    @RequestMapping(value = "/form")
    public ModelAndView user() {
        return new ModelAndView("form", "user", new User());
    }

    @RequestMapping(value = "/result")
    public ModelAndView processUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("u", user);
        modelAndView.setViewName("result");

        return modelAndView;
    }

}
