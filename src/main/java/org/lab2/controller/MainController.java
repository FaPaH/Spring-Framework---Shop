package org.lab2.controller;

import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceProduct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    private UserServiceProduct userServiceProduct;

    private UserServiceCategory userServiceCategory;

    @Autowired
    public void setUserServiceCategory(UserServiceCategory userServiceCategory) {
        this.userServiceCategory = userServiceCategory;
    }

    @Autowired
    public void setUserServiceProduct(UserServiceProduct userServiceProduct) {
        this.userServiceProduct = userServiceProduct;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        logger.debug("main index page");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexI(ModelAndView modelAndView) {
        logger.debug("main index page");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/list",  method = RequestMethod.GET)
    public ModelAndView productList(ModelAndView modelAndView){
        modelAndView.addObject("productList", this.userServiceProduct.getAllProducts());
        modelAndView.setViewName("/list");
        logger.debug("productList page");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ModelAndView categoryList(ModelAndView modelAndView){
        modelAndView.addObject("categoryList", this.userServiceCategory.getAllCategory());
        modelAndView.setViewName("categoryList");
        logger.debug("categoryList page");
        return modelAndView;
    }
}