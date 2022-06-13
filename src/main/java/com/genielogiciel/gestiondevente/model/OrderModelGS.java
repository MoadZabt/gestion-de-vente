package com.genielogiciel.gestiondevente.model;

import com.genielogiciel.gestiondevente.domain.Order;
import com.genielogiciel.gestiondevente.domain.OrderGS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModelGS extends AbstractModelGS<OrderGS> {

    public OrderModelGS(){
        super(OrderGS.class);
    }

    public List<OrderGS> findByDate(LocalDate date) {
        List<OrderGS> orderInDate = new ArrayList<>();
        findAll().forEach(order -> {
            if(order.getShippingDate().isEqual(date)) {
                orderInDate.add(order);
            }
        });
        System.out.println(orderInDate);
        return orderInDate;
    }
}
