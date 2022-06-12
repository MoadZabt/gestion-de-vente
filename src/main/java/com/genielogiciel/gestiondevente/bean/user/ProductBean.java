package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Order;
import com.genielogiciel.gestiondevente.domain.OrderDetails;
import com.genielogiciel.gestiondevente.domain.Product;
import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.model.OrderDetailsModel;
import com.genielogiciel.gestiondevente.model.OrderModel;
import com.genielogiciel.gestiondevente.model.ProductModelGS;
import com.genielogiciel.gestiondevente.service.UserService;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean implements Serializable {

    private ProductModelGS productModel = new ProductModelGS();

    private OrderModel orderModel = new OrderModel();

    private OrderDetailsModel orderDetailsModel = new OrderDetailsModel();

    private UserService userService = new UserService();

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
        String productsToOrder = params.get("productsToOrder");
        if(productsToOrder != null) {
            JSONArray jsonArray = new JSONArray(productsToOrder);
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            List<Order> orderList = new ArrayList<>();
            User user = userService.findById(22L);
            float totalPrice = 0.0f;
            System.out.println(jsonArray);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                Product product = productModel.find(Long.parseLong(jsonObject.get("id").toString()));
                int productQuantity = Integer.parseInt(jsonObject.get("count").toString());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setProductId(product.getId());
                orderDetails.setQuantity(productQuantity);
                orderDetails.setTotalPrice(Float.parseFloat(jsonObject.get("total").toString()));
                orderDetailsModel.create(orderDetails);

                orderDetailsList.add(orderDetails);
                totalPrice += orderDetails.getTotalPrice();
            }
            Order order = new Order();
            order.setOrderDetails(orderDetailsList);
            order.setTotalPrice(totalPrice);
            order.setShipped(false);
            order.setShippingDate(LocalDate.now().plusDays(7));
            order.setUser(user);
            orderModel.create(order);
            orderList.add(order);
        }
        this.rowsPerPage = 12;
        this.totalRows = productModel.size();
        this.totalPages = (int) (Math.ceil((totalRows - 1) / rowsPerPage));

        for(int page = 0; page <= totalPages; page++){
            pages.add(String.valueOf(page));
        }

        int fromRow = currentPage * rowsPerPage;
        this.products = productModel.findAllPaginated(fromRow, rowsPerPage);
    }

}
