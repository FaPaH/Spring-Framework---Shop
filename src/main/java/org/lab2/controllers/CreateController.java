package org.lab2.controllers;

import org.apache.log4j.Logger;
import org.lab2.services.UserServiceCategory;
import org.lab2.services.UserServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateController {

    private static Logger logger = Logger.getLogger(CreateController.class);

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addProduct(ModelAndView modelAndView, @RequestParam("name") String name,
                                      @RequestParam("prise") int prise, @RequestParam("categoryId") int categoryId){
        userServiceProduct.addProduct(name, prise, categoryId);
        modelAndView.setViewName("redirect:/list");
        logger.debug("add product with name: " + name);
        return modelAndView;
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(ModelAndView modelAndView){
        modelAndView.addObject("categoryList", userServiceCategory.getAllCategory());
        modelAndView.setViewName("add");
        logger.debug("edit page");
        return modelAndView;
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public ModelAndView addCategory(ModelAndView modelAndView, @RequestParam("categoryName") String name){
        userServiceCategory.addCategory(name);
        modelAndView.setViewName("redirect:categoryList");
        logger.debug("add category with name: " + name);
        return modelAndView;
    }
}
