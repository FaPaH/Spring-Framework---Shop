package org.lab2.controller;

import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceDelivery;
import org.lab2.service.UserServiceProduct;
import org.apache.log4j.Logger;
import org.lab2.service.UserServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class CreateController {

    private static Logger logger = Logger.getLogger(CreateController.class);

    private UserServiceProduct userServiceProduct;

    private UserServiceCategory userServiceCategory;

    private UserServiceDelivery userServiceDelivery;

    private UserServiceUser userServiceUser;

    @Autowired
    public void setUserServiceUser(UserServiceUser userServiceUser) {
        this.userServiceUser = userServiceUser;
    }

    @Autowired
    public void setUserServiceCategory(UserServiceCategory userServiceCategory) {
        this.userServiceCategory = userServiceCategory;
    }

    @Autowired
    public void setUserServiceProduct(UserServiceProduct userServiceProduct) {
        this.userServiceProduct = userServiceProduct;
    }

    @Autowired
    public void setUserServiceDelivery(UserServiceDelivery userServiceDelivery) {
        this.userServiceDelivery = userServiceDelivery;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addProduct(ModelAndView modelAndView, @RequestParam("name") String name,
                                      @RequestParam("prise") int prise, @RequestParam("categoryId") int categoryId){
        userServiceProduct.addProduct(name, prise, categoryId);
        modelAndView.setViewName("redirect:/list");
        logger.debug("add product with name: " + name);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(ModelAndView modelAndView){
        modelAndView.addObject("categoryList", userServiceCategory.getAllCategory());
        modelAndView.setViewName("add");
        logger.debug("edit page");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public ModelAndView addCategory(ModelAndView modelAndView, @RequestParam("categoryName") String name){
        userServiceCategory.addCategory(name);
        modelAndView.setViewName("redirect:/categoryList");
        logger.debug("add category with name: " + name);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/createDelivery", method = RequestMethod.POST)
    public ModelAndView createDelivery(Principal principal, ModelAndView modelAndView, @RequestParam("name") String name,
                                       @RequestParam("address") String address, @RequestParam("phoneNumber") int phoneNumber,
                                       @RequestParam("totalPrise") int totalPrise, @RequestParam("checkedListId") List<Integer> checkedListId){

        System.out.println("checkList in create " + checkedListId);
        userServiceDelivery.addDelivery(userServiceUser.getUserIdByLogin(principal.getName()),
                name, checkedListId, address, phoneNumber, totalPrise);
        modelAndView.setViewName("redirect:/checkDelivery");
        logger.debug("add category with name: " + name);
        return modelAndView;
    }
}
