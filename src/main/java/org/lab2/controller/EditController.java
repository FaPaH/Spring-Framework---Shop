package org.lab2.controller;

import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceProduct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditController {

    private static Logger logger = Logger.getLogger(EditController.class);

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateProduct(ModelAndView modelAndView, @RequestParam("productId") int id,
                                      @RequestParam("name") String name, @RequestParam("prise") int prise,
                                      @RequestParam("categoryId") int categoryId){
        userServiceProduct.updateProduct(id, name, prise, categoryId);
        modelAndView.setViewName("redirect:/list");
        logger.debug("update product with id: " + id);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{productId}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("productId") int productId){
        modelAndView.addObject("product", userServiceProduct.findProductById(productId));
        modelAndView.addObject("categoryList", userServiceCategory.getAllCategory());
        modelAndView.setViewName("edit");
        logger.debug("edit page");
        return modelAndView;
    }
}