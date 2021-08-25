package com.example.onlineAuction.controller;

import com.example.onlineAuction.dto.BidDto;
import com.example.onlineAuction.dto.ProductDto;
import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.service.ProductService;
import com.example.onlineAuction.service.UserService;
import com.example.onlineAuction.validator.BidDtoValidator;
import com.example.onlineAuction.validator.ProductDtoValidator;
import com.example.onlineAuction.validator.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.onlineAuction.service.BidService;


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

    @Autowired
    private BidDtoValidator bidDtoValidator;

    @Autowired
    private BidService bidService;

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


    @GetMapping({"/home", "/"})
    public String getHome(Model model, Authentication authentication) {
        List<ProductDto> productDtoList = productService.getAllProductDtos(authentication.getName());
        model.addAttribute("products", productDtoList);
        return "home";
    }

    @GetMapping(value = "/item/{productId}")
    public String getProductPage(@PathVariable(value = "productId") String productId, Model model, Authentication authentication) {
        Optional<ProductDto> optionalProductDtoFound = productService.getProductDtoById(productId, authentication.getName());
        if (!optionalProductDtoFound.isPresent()) {
            return "errorPage";
        }
        BidDto bidDto= new BidDto();
        model.addAttribute("bidDto", bidDto);
        ProductDto productDtoFound = optionalProductDtoFound.get();
        model.addAttribute("product", productDtoFound);
        return "viewItem";
    }

    @PostMapping("/item/{productId}")
    public String postProductPage(BidDto bidDto, BindingResult bindingResult,
                                  @PathVariable(value = "productId") String productId,
                                  Authentication authentication, Model model) {
        bidDtoValidator.validate(bidDto, bindingResult, productId);
        if (bindingResult.hasErrors()) {
            Optional<ProductDto> optionalProductDtoFound = productService.getProductDtoById(productId, authentication.getName());
            if (!optionalProductDtoFound.isPresent()) {
                return "errorPage";
            }
//            model.addAttribute("bid", bidDto);
            model.addAttribute("product", optionalProductDtoFound.get());
            return "viewItem";
        }
        System.out.println("am primit bid value " + bidDto.getValue() + " pentru produsul cu id-ul "+ productId);
        bidService.placeBid(bidDto, productId, authentication.getName());
        return "redirect:/item/"+ productId;
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistrationPage(Model model, UserDto userDto, BindingResult bindingResult) {
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser(userDto);
        return "redirect:/home";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {

        return "login";
    }

    @GetMapping(value = "/login-error")
    public String getLoginErrorPage(Model model) {

        model.addAttribute("loginError", true);
        return "login";
    }

}
