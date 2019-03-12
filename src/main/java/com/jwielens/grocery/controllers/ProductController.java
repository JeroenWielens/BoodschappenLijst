package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;


@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getProducten());
        log.debug("Producten ophalen");
        return "listProducts";
    }

    @GetMapping("/{id}")
    public String getProduct(Model model, @PathVariable Long id) {
        model.addAttribute("products", productService.findById(id));
        log.debug("enkel product ophalen");
        return "listProducts";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Vul alle velden in");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/products/new";
        } else {
            redirectAttributes.addFlashAttribute("message", "Succes");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            productService.saveOrUpdate(product);
            return "redirect:/products/";
        }
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "newProduct";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/lijst")
    public String getLijst(Model model){
        Set<Product>lijstje = productService.getProducten();
        for (Product product:productService.getProducten()){
            if (product.getBenodigdeHoeveelheid()<1){
                lijstje.remove(product);
            }
        }
        model.addAttribute("products", lijstje);
        return "boodschappenLijst";
    }
}

