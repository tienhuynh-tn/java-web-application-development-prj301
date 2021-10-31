/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tienhlt.orderdetails.OrderDetailsDAO;
import tienhlt.orderdetails.OrderDetailsDTO;
import tienhlt.orders.OrdersDAO;
import tienhlt.product.ProductDAO;
import tienhlt.product.ProductDTO;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class CartObject implements Serializable {
    private Map<ProductDTO, Integer> items;
    
    public Map<ProductDTO, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String SKU) 
        throws SQLException, NamingException{
        //1. Checking items has existed
        if (SKU == null) {
            return;
        }
        
        if (SKU.trim().isEmpty()) {
            return;
        }
        
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        
        //2. Checking item exited in items
        int quantity = 1;
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySKU(SKU);
        
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }
        
        //3. Update items
        this.items.put(dto, quantity);
    }
    
    public void removeItemFromCart(String SKU) 
        throws SQLException, NamingException{
        //1. checking items has existed
        if (this.items == null) {
            return;
        }
        //2. checking item existed in cart
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySKU(SKU);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public Map<ProductDTO, Integer> showCheckedItems(String[] SKU) 
        throws SQLException, NamingException{
        if (this.items == null) {
            return null;
        }
        
        Map<ProductDTO, Integer> list = new HashMap<>();
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        for (String sku : SKU) {
            dto = dao.getProductBySKU(sku);
            list.put(dto, items.get(dto));
        }
        return list;
    }
    
    public boolean checkOutItemsOfCart(String name, String address, String total, Map<ProductDTO, Integer> checkedItems) 
        throws SQLException, NamingException{
        if (this.items == null) {
            return false;
        }
        
        OrdersDAO ordersDAO = new OrdersDAO();
        int orderID = ordersDAO.createNewOrder(name, address, total);
        
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        boolean result = orderDetailsDAO.createOrderDetails(orderID, checkedItems);
        
        return result;
    }
    
    public int getQuantityBySKU(String SKU) {
        if (SKU == null || SKU.trim().isEmpty()) {
            return 0;
        }
        
        if (this.items == null) {
            return 0;
        }
        
        int quantity = 0;
        
        for (ProductDTO dto : this.items.keySet()) {
            if (SKU.equals(dto.getSKU())) {
                quantity = this.items.get(dto);
                return quantity;
            }
        }
        
        return 0;
    }
    
    public List<OrderDetailsDTO> addItemsToOrderDetailsDTO(Map<ProductDTO, Integer> checkedItems, int orderID) {
        List<OrderDetailsDTO> list = new ArrayList<>();
        
        for (ProductDTO productDTO : checkedItems.keySet()) {
            String SKU = productDTO.getSKU();
            String name = productDTO.getName();
            BigDecimal price = productDTO.getPrice();
            int quantity = checkedItems.get(productDTO);
            BigDecimal total;
            total = price.multiply(new BigDecimal(quantity));
            
            OrderDetailsDTO orderDetailsDTO = 
                    new OrderDetailsDTO(orderID, SKU, name, price, quantity, total);
            
            list.add(orderDetailsDTO);
        }
        
        return list;
    }
//    private Map<String, Integer> items;
//
//    public Map<String, Integer> getItems() {
//        return items;
//    }
//    
//    public void addItemToCart(String name) {
//        // 1. Checking items has existed
//        if (name == null) {
//            return;
//        }
//        if (name.trim().isEmpty()) {
//            return;
//        }
//        if (this.items == null) {
//            this.items = new HashMap<>();
//        }
//        //2. Checking item existed in items
//        int quantity = 1;
//        if (this.items.containsKey(name)) {
//            quantity = this.items.get(name) + 1;
//        }
//        //3. Update items
//        this.items.put(name, quantity);
//    }
//    
//    public void removeItemFromCart(String name) {
//        //1. checking items has existed
//        if (this.items == null) {
//            return;
//        }
//        //2. checking item existed in cart
//        if (this.items.containsKey(name)) {
//            this.items.remove(name);
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }
//        }
//    }
}
