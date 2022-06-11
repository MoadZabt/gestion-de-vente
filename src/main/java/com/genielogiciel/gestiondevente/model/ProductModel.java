package com.genielogiciel.gestiondevente.model;

import com.genielogiciel.gestiondevente.domain.*;

import java.io.Serializable;

public class ProductModel extends AbstractModel<Product> implements Serializable {

    public ProductModel(){
        super(Product.class);
    }
}

