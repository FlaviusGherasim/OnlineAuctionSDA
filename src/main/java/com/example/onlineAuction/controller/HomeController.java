package com.example.onlineAuction.controller;

import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.service.ProductService;
import com.example.onlineAuction.service.UserService;
import com.example.onlineAuction.validator.ProductDtoValidator;
import com.example.onlineAuction.validator.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDtoValidator productDtoValidator;

    @Autowired
    private UserDtoValidator userDtoValidator;

    @GetMapping(value = "/addItem")
    public String getAddItemPage(Model model) {
        System.out.println("Rulez get pe /addItem");
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addItem";
    }

    @PostMapping(value = "/addItem")
    public String postAddItemPage(Model model, ProductDto productDto, BindingResult bindingResult, @RequestParam("productImage") MultipartFile multipartFile) {
        System.out.println("Am primit: " + multipartFile);
        productDtoValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        productService.addProduct(productDto, multipartFile);
        return "redirect:/addItem"; //rulez redirect catre get
    }

//    @GetMapping(value = "/addUser")
//    public String getAddUserPage(Model model) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("userDto", userDto);
//        return "addUser";
//    }

//    @PostMapping(value = "/addUser")
//    public String postAddItemPage(Model model, UserDto userDto) {
//        System.out.println("Am primit: " + userDto);
//        userService.addUser(userDto);
//        return "addUser";
//    }

    @GetMapping(value = "/home")
    public String getHome(Model model) {
        List<ProductDto> productDtoList = productService.getAllProductDtos();
        model.addAttribute("products", productDtoList);
        return "home";
    }

    @GetMapping(value = "/item/{productId}")
    public String getProductPage(@PathVariable(value = "productId") String productId, Model model) {
        Optional<ProductDto> optionalProductDtoFound = productService.getProductDtoById(productId);
        if (!optionalProductDtoFound.isPresent()) {
            return "errorPage";
        }
        ProductDto productDtoFound = optionalProductDtoFound.get();
        model.addAttribute("product", productDtoFound);
        return "viewItem";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(Model model, UserDto userDto, BindingResult bindingResult)
    {
        userDtoValidator.validate(userDto, bindingResult);
        if(bindingResult.hasErrors())
        {
            return "registration";
        }
        userService.addUser(userDto);
        return "redirect:/home";
    }

}
