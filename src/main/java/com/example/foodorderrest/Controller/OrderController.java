package com.example.foodorderrest.Controller;

import com.example.foodorderrest.Model.MenuElement;
import com.example.foodorderrest.Model.Order;
import com.example.foodorderrest.Service.Menu;
import com.example.foodorderrest.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Menu menu;
    private Order currOrder;

    @GetMapping("")
    public String getOrder(Model m) {
        if (currOrder == null) {
            currOrder = new Order();
            currOrder.setId(orderService.getCounter());
        }
        m.addAttribute("dishes", currOrder.getDishes());
        m.addAttribute("total", currOrder.countTotal());
        return "order";
    }

    @PostMapping("/add/{id}")
    public String addDish(@PathVariable("id") int dishId, Model m) {
        if (currOrder == null) {
            currOrder = new Order();
            currOrder.setId(orderService.getCounter());
        }
        MenuElement dish = menu.getElement(dishId);
        if (!currOrder.containsDish(dish)) {
            currOrder.addDish(menu.getElement(dishId));
            m.addAttribute("orderId", currOrder.getId());
        }
        return "redirect:/";
    }

    @PostMapping("/make")
    public String makeOrder(){
        if (currOrder != null)
            orderService.put(currOrder);
        currOrder = null;
        return "redirect:/";
    }
}
