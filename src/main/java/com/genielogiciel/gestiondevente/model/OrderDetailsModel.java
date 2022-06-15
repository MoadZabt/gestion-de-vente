package com.genielogiciel.gestiondevente.model;

import com.genielogiciel.gestiondevente.domain.OrderDetails;

import java.io.Serializable;

public class OrderDetailsModel extends AbstractModel<OrderDetails> implements Serializable {

    public OrderDetailsModel() {
        super(OrderDetails.class);
    }
}
