package com.example.foodorderrest.Model;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@Data
public class Order {
    private int id;
    private List<MenuElement> dishes;
    private boolean served;

    public Order() {
        this.dishes = new ArrayList<>();
        served = false;
    }

    public void addDish(MenuElement me){
        dishes.add(me);
    }

    public boolean containsDish(MenuElement me) {
        return dishes.contains(me);
    }

    public double countTotal() {
        return dishes.stream().mapToDouble(MenuElement::getPrice).sum();
    }
}
