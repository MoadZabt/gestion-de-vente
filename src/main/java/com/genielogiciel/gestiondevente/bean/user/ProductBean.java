package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Product;
import com.genielogiciel.gestiondevente.model.ProductModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean implements Serializable {

    private ProductModel productModel = new ProductModel();

    private List<Product> products;

    private Long totalRows;
    private int firstRow;
    private int rowsPerPage;
    private int totalPages;
    private int pageRange;

    private List<String> pages = new ArrayList<>();

    private int currentPage;

    public ProductBean() {
        getProductPaginated();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }
    public int getFirstRow() {
        return firstRow;
    }
    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }
    public int getRowsPerPage() {
        return rowsPerPage;
    }
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getPageRange() {
        return pageRange;
    }
    public void setPageRange(int pageRange) {
        this.pageRange = pageRange;
    }
    public List<String> getPages() {
        return pages;
    }
    public void setPages(List<String> pages) {
        this.pages = pages;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void getProductPaginated() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        this.currentPage = Integer.parseInt(params.get("currentPage") == null ? "0" : params.get("currentPage"));
        System.out.println(currentPage);
        this.rowsPerPage = 12;
        System.out.println(this.rowsPerPage);
        this.totalRows = productModel.size();
        System.out.println(this.totalRows);
        this.totalPages = (int) (Math.ceil((totalRows - 1) / rowsPerPage));

        for(int page = 0; page <= totalPages; page++){
            pages.add(String.valueOf(page));
        }

        System.out.println(this.totalPages);
        int fromRow = currentPage * rowsPerPage;
        System.out.println(fromRow);
        this.products = productModel.findAllPaginated(fromRow, rowsPerPage);
        System.out.println(this.pages);
    }

}
