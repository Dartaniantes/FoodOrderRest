package com.example.foodorderrest.Controller;

import com.example.foodorderrest.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    @GetMapping
    public String getAll(Model m) {
        m.addAttribute("orders", orderService.getAll());
        return "orders";
    }

    @PostMapping("/serve/{id}")
    public String serve(@PathVariable("id") int id, Model m){
        orderService.getOrder(id).setServed(true);
        m.addAttribute("orders", orderService.getAll());
        return "/orders";
    }
}
