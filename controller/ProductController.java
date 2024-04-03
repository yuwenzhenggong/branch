package com.cy.store.controller;

import com.cy.store.model.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public BaseResponse<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new BaseResponse<List<Product>>(OK, data);
    }

    @RequestMapping("{id}/details")
    public BaseResponse<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return new BaseResponse<Product>(OK, data);
    }
}
