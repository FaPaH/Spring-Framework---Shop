package org.lab2.controller;

import org.apache.log4j.Logger;
import org.lab2.service.UserServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    private static Logger logger = Logger.getLogger(RegistrationController.class);

    private UserServiceUser userServiceUser;

    @Autowired
    public void setUserServiceUser(UserServiceUser userServiceUser) {
        this.userServiceUser = userServiceUser;
    }

    @RequestMapping(value = "/registrationPage", method = RequestMethod.GET)
    public ModelAndView registrationPage(ModelAndView modelAndView){
        modelAndView.setViewName("/registrationPage");
        logger.debug("registration page");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationUser", method = RequestMethod.POST)
    public ModelAndView registrationUser(ModelAndView modelAndView,
                                         @RequestParam(value = "userLogin") String login,
                                         @RequestParam(value = "userPassword") String password){
        if (!userServiceUser.isUserExist(login.toLowerCase())){
            modelAndView.addObject("msg", "This user is already exist");
            modelAndView.setViewName("/registrationPage");
        } else {
            userServiceUser.createUser(login.toLowerCase(), password);
            modelAndView.addObject("msg", "Now you can login as: " + login);
            modelAndView.setViewName("/index");
        }
        logger.debug("register new user with name " + login);
        return modelAndView;
    }
}
