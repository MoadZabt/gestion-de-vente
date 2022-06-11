package com.genielogiciel.gestiondevente.domain;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    public Product() {
        super();
    }

    public Product(String description, String name, float price, int quantity, String path) {

        super();
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float price;

    @Column
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInventoryStatus() {
        return this.quantity >= 10 ? "instock" : (this.quantity <= 0 ? "outofstock" : "lowstock");
    }

    public String getImagePath() {
        String imgPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        System.out.println(imgPath);
        return imgPath;
    }

}
