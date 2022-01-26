package org.lab2.controller;

import org.lab2.model.Delivery;
import org.lab2.model.Products;
import org.lab2.service.UserServiceCategory;
import org.lab2.service.UserServiceDelivery;
import org.lab2.service.UserServiceProduct;
import org.apache.log4j.Logger;
import org.lab2.service.UserServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    private int userId;

    private List<Delivery> deliveryList;

    private UserServiceProduct userServiceProduct;

    private UserServiceCategory userServiceCategory;

    private UserServiceDelivery userServiceDelivery;

    private UserServiceUser userServiceUser;

    @Autowired
    public void setUserServiceUser(UserServiceUser userServiceUser) {
        this.userServiceUser = userServiceUser;
    }


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
    public ModelAndView productList(Principal principal, ModelAndView modelAndView){
        modelAndView.addObject("productList", userServiceProduct.getAllProducts());
        modelAndView.addObject("msg", "You login as: " + principal.getName());
        modelAndView.setViewName("/list");
        logger.debug("productList page");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ModelAndView categoryList(ModelAndView modelAndView){
        modelAndView.addObject("categoryList", userServiceCategory.getAllCategory());
        modelAndView.setViewName("categoryList");
        logger.debug("categoryList page");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/checkList", method = RequestMethod.POST)
    public ModelAndView checkList(ModelAndView modelAndView,
                                  @RequestParam(value = "checkedProductId", defaultValue = "null") List<String> checkedProductId){

        List<Products> productList = new ArrayList<>();
        int totalPrise = 0;

        System.out.print("checkList " + checkedProductId);

        if(!checkedProductId.get(0).equals("null")){
            for(String str : checkedProductId){
                int id = Integer.parseInt(str);
                Products product = userServiceProduct.findProductById(id);
                totalPrise += product.getProductPrise();
                productList.add(product);
            }
        } else {
            modelAndView.addObject("msg", "You didnt click on any product");
        }

        System.out.print("checkList after check" + productList);

        modelAndView.addObject("deliveryList", productList);
        modelAndView.addObject("totalPrise", totalPrise);
        modelAndView.setViewName("deliveryList");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/checkDelivery", method = RequestMethod.GET)
    public ModelAndView checkDelivery(Principal principal, ModelAndView modelAndView){
        userId = userServiceUser.getUserIdByLogin(principal.getName());
        deliveryList = userServiceDelivery.findDeliveryByUserId(userId);

        if (deliveryList.isEmpty()){
            modelAndView.addObject("msg", "Your delivery list is empty");
        } else {
            modelAndView.addObject("deliveryList", userServiceDelivery.findDeliveryByUserId(userId));
        }

        modelAndView.setViewName("/checkDelivery");
        logger.debug("categoryList page");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/checkAllDelivery", method = RequestMethod.GET)
    public ModelAndView checkAllDelivery(ModelAndView modelAndView){

        deliveryList = userServiceDelivery.getAllDelivery();

        if (deliveryList.isEmpty()){
            modelAndView.addObject("msg", "Your delivery list is empty");
        } else {
            modelAndView.addObject("deliveryList", deliveryList);
        }

        modelAndView.setViewName("/checkDelivery");
        logger.debug("categoryList page");
        return modelAndView;
    }
}