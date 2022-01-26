package org.lab2.controller;

import org.apache.log4j.Logger;
import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    public ModelAndView searchByName(ModelAndView modelAndView, @RequestParam(value = "searchName", defaultValue = "") String searchName) {

        System.out.println();
        if (searchName.equals("")){
            modelAndView.addObject("productList", userServiceProduct.getAllProducts());
        } else {
            modelAndView.addObject("productList", userServiceProduct.findProductByName(searchName));
        }
        logger.debug("searching product by " + searchName);
        modelAndView.setViewName("/list");
        return modelAndView;
    }
}
