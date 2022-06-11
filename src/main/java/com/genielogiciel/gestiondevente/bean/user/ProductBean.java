package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Product;
import com.genielogiciel.gestiondevente.model.ProductModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean implements Serializable {

    private ProductModel productModel = new ProductModel();

    private List<Product> products;

    public ProductBean() {
        this.products = productModel.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
