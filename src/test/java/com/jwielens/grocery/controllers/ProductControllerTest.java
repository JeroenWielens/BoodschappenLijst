package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        controller = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllProducts() throws Exception {
        Set<Product> products = new HashSet<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.getProducten()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("listProducts"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void getProduct() throws Exception {
        Product product = new Product();
        when(productService.findById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("listProducts"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void newProduct() throws Exception {
        Long id = 2L;

        verifyZeroInteractions(productService);

        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("newProduct"));
    }

    @Test
    public void saveProduct() throws Exception {
    }

    @Test
    public void editProduct() throws Exception {
        Long id = 1L;

        when(productService.findById(id)).thenReturn(new Product());

        mockMvc.perform(get("/products/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("newProduct"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void delete() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"));

        verify(productService, times(1)).delete(id);
    }
}