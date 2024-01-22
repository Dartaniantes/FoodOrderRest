package com.example.foodorderrest.Service;

import com.example.foodorderrest.Model.MenuElement;
import com.example.foodorderrest.Model.Order;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class OrderService {
    private List<Order> ordersRepository;
    private int counter;

    public OrderService() {
        ordersRepository = new ArrayList<>();
        counter = 0;
    }

    public void put(Order o) {
        o.setId(counter);
        ordersRepository.add(o);
        counter++;
    }

    public List<Order> getAll() {
        return ordersRepository;
    }


    public Order getOrder(int id) {
        return ordersRepository.stream().filter(o -> o.getId() == id).findFirst().get();
    }
}
