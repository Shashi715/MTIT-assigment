package com.mtit.assignment.microservice.stockservice.Controller;

import com.mtit.assignment.microservice.stockservice.Model.Response;
import com.mtit.assignment.microservice.stockservice.Model.Stock;
import com.mtit.assignment.microservice.stockservice.Model.StockRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    //Stores all the product data
    private static List<Stock> stockList =  new ArrayList<>();

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    boolean saveOrUpdateStock(@RequestBody StockRequest stockRequest){

        System.out.println("Request: " + stockRequest.toString());

        boolean oldStock = false;

        for(Stock temp : stockList){
            if(temp.getProductId() == stockRequest.getProductId()){
                oldStock = true;
            }
        }

        if(oldStock == true){
            // Update
            List<Stock> tempList = new ArrayList<>();
            for(Stock temp : stockList){
                if(temp.getProductId() == stockRequest.getProductId()){
                    temp.setProductId(stockRequest.getProductId());
                    temp.setQuantity(stockRequest.getQuantity() + temp.getQuantity());

                    tempList.add(temp);
                    System.out.println("Output: " + temp.toString());
                }
                else{
                    tempList.add(temp);
                }
            }

            stockList = new ArrayList<>();
            stockList = tempList;
        }
        else{
            // Save
            Stock stock = new Stock();

            int id = stockList.size() + 1;
            stock.setId(id);
            stock.setProductId(stockRequest.getProductId());
            stock.setQuantity(stockRequest.getQuantity());

            stockList.add(stock);
            System.out.println("Output: " + stock.toString());

        }

        System.out.println("########## FOR ##########");
        for(Stock temp : stockList){
            System.out.println("* " + temp.toString());
        }

        return true;
    }

    @GetMapping("viewStock/{productId}")
    public @ResponseBody Stock viewStock(@PathVariable int productId){

        System.out.println("Request: " + productId);

        for(Stock temp : stockList){
            if(temp.getProductId() == productId){
                return temp;
            }
        }

        return null;
    }
}
