package org.lab2.controller;

import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceDelivery;
import org.lab2.service.UserServiceProduct;
import org.apache.log4j.Logger;
import org.lab2.service.UserServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteController {

    private static Logger logger = Logger.getLogger(DeleteController.class);

    private UserServiceProduct userServiceProduct;

    private UserServiceCategory userServiceCategory;

    private UserServiceDelivery userServiceDelivery;

    @Autowired
    public void setUserServiceDelivery(UserServiceDelivery userServiceDelivery) {
        this.userServiceDelivery = userServiceDelivery;
    }


    @Autowired
    public void setUserServiceCategory(UserServiceCategory userServiceCategory) {
        this.userServiceCategory = userServiceCategory;
    }

    @Autowired
    public void setUserServiceProduct(UserServiceProduct userServiceProduct) {
        this.userServiceProduct = userServiceProduct;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(ModelAndView modelAndView, @PathVariable("productId") int productId){
        userServiceProduct.removeProduct(productId);
        modelAndView.setViewName("redirect:/list");
        logger.debug("delete product with id: " + productId);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteCategory/{categoryId}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(ModelAndView modelAndView, @PathVariable("categoryId") int categoryId){
        userServiceCategory.removeCategory(categoryId);
        modelAndView.setViewName("redirect:/categoryList");
        logger.debug("delete category with id: " + categoryId);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteDelivery/{deliveryId}", method = RequestMethod.GET)
    public ModelAndView deleteDelivery(ModelAndView modelAndView, @PathVariable("deliveryId") int deliveryId){
        userServiceDelivery.removeDelivery(deliveryId);
        modelAndView.setViewName("redirect:/checkDelivery");
        logger.debug("delete category with id: " + deliveryId);
        return modelAndView;
    }
}
