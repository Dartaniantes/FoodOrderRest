package com.example.foodorderrest.Service;

import com.example.foodorderrest.Model.MenuElement;
import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Getter
@Service
public class Menu {
    private List<MenuElement> menuElementsRepo;
    private int counter;

    public Menu(){
        counter = 1;
        menuElementsRepo = new ArrayList<>();
        createElement(new MenuElement(counter, "Mashed potato", 85.5, "Mashed boiled potato seasoned with milk"));
        createElement(new MenuElement(counter, "Pasta", 109, "Classic italian pasta"));
    }

    public void createElement(MenuElement me){
        me.setId(counter);
        counter++;
        menuElementsRepo.add(me);
    }

    public List<MenuElement> getElements(List<Integer> ids){
        List<MenuElement> mes = new ArrayList<>();
        for (int id : ids) {
            mes.add(menuElementsRepo.stream().filter(me -> me.getId() == id).findFirst().get());
        }
        return mes;
    }

    public MenuElement getElement(int id){
        return menuElementsRepo.stream().filter(me -> me.getId() == id).findFirst().get();
    }

    public boolean remove(int id){
        return menuElementsRepo.removeIf(me -> me.getId() == id);
    }

}
