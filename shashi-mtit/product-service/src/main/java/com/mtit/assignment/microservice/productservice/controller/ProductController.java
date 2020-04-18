package com.mtit.assignment.microservice.productservice.controller;

import com.mtit.assignment.microservice.productservice.model.Product;
import com.mtit.assignment.microservice.productservice.model.ProductRequest;
import com.mtit.assignment.microservice.productservice.model.Response;
import com.mtit.assignment.microservice.productservice.model.StockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    //Stores all the product data
    private static List<Product> productList =  new ArrayList<>();

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    Response createOrder(@RequestBody ProductRequest productRequest){

        System.out.println("Request: " + productRequest.toString());

        StockRequest stockRequest = new StockRequest();
        Response response = new Response();
        String message = "Message: ";
        response.setId(UUID.randomUUID().toString());
        boolean oldProduct = false;

        for(Product temp : productList){
            if(temp.getProductid().equals(productRequest.getProductid())){
                oldProduct = true;
            }
        }

        if(oldProduct == true){
            // Update
            List<Product> tempList = new ArrayList<>();
            for(Product temp : productList){
                if(temp.getProductid().equals(productRequest.getProductid())){
                    temp.setUnitPrice(productRequest.getUnitPrice());
                    temp.setProductName(productRequest.getProductName());

                    tempList.add(temp);
                    message += "Product updated Successfully || ";
                    System.out.println("Output: " + temp.toString());

                    stockRequest.setProductId(temp.getId());
                    stockRequest.setQuantity(productRequest.getQty());
                }
                else{
                    tempList.add(temp);
                }
            }

            productList = new ArrayList<>();
            productList = tempList;

            // Access stock service to update stock

            String url1 = "http://localhost:9090/stock";
            ResponseEntity<Boolean> status = restTemplate.postForEntity(url1, stockRequest, Boolean.class);
            if(status.getBody() == true){
                System.out.println("Stock successfully added or updated");
            }

        }
        else{
            // Save
            Product product = new Product();

            int id = productList.size() + 1;
            product.setId(id);
            product.setProductid(productRequest.getProductid());
            product.setProductName(productRequest.getProductName());
            product.setUnitPrice(productRequest.getUnitPrice());

            productList.add(product);
            message += "Product added Successfully || ";
            System.out.println("Output: " + product.toString());

            stockRequest.setProductId(id);
            stockRequest.setQuantity(productRequest.getQty());

            // Access stock service to add stock

            String url1 = "http://localhost:9090/stock";
            ResponseEntity<Boolean> status = restTemplate.postForEntity(url1, stockRequest, Boolean.class);
            if(status.getBody() == true){
                System.out.println("Stock successfully added or updated");
            }

        }

        response.setMessage(message);

        System.out.println("########## FOR ##########");
        for(Product temp : productList){
            System.out.println("* " + temp.toString());
        }

        return response;

    }

    @GetMapping("viewProduct/{productId}")
    public @ResponseBody Product viewProduct(@PathVariable String productId){

        System.out.println("Request: " + productId);

        for(Product temp : productList){
            if(temp.getProductid().equals(productId)){
                return temp;
            }
        }

        return null;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
