package org.lab2.controller;

import org.apache.log4j.Logger;
import org.lab2.service.UserServiceAPIImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class APIController {

    private static Logger logger = Logger.getLogger(APIController.class);

    private UserServiceAPIImpl userServiceAPIImpl;

    @Autowired
    public void setUserServiceAPI(UserServiceAPIImpl userServiceAPIImpl) {
        this.userServiceAPIImpl = userServiceAPIImpl;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView getMovie(ModelAndView modelAndView,
                              @RequestParam(value = "movieName", defaultValue = "No value") String movieName) {
        logger.info("Calling getMovie");

        String msg = userServiceAPIImpl.getMessage(movieName);
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("message");
        return modelAndView;
    }
}
