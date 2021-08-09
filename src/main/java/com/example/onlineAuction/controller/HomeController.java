package com.example.onlineAuction.controller;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.service.ProductService;
import com.example.onlineAuction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/addItem")
    public String getAddItemPage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "add-item";
    }

    @PostMapping(value = "/addItem")
    public String postAddItemPage(Model model, ProductDto productDto) {
        System.out.println("Am primit: " + productDto);
        productService.addProduct(productDto);
        return "add-item";
    }

    @GetMapping(value = "/addUser")
    public String getAddUserPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "add-user";
    }

    @PostMapping(value = "/addUser")
    public String postAddItemPage(Model model, UserDto userDto) {
        System.out.println("Am primit: " + userDto);
        userService.addUser(userDto);
        return "add-user";
    }

}
