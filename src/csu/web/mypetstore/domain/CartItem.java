package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 6620528781626504362L;

    private Item item;
    private String userId;
    private String itemId;
    private int lineNumber;
    private String attr;
    private String productId;
    private int quantity;
    private boolean inStock;
    private BigDecimal listPrice;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public String getUserId(){return userId;}
    public void setUserId(String userId){
        this.userId = userId;
    }
    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }

    public BigDecimal getListPrice() {return listPrice;}
    public void setListPrice(BigDecimal listPrice){
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitPrice(){return unitPrice;}
    public void setUnitPrice(BigDecimal unitPrice){
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateTotal();
    }

    public String getItemId(){return itemId;}

    public void setItemId(String itemId){
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void incrementQuantity() {
        quantity++;
        calculateTotal();
    }

    private void calculateTotal() {
        if (item != null && item.getListPrice() != null) {
            total = item.getListPrice().multiply(new BigDecimal(quantity));
        } else {
            total = null;
        }
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "item=" + item +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", lineNumber=" + lineNumber +
                ", attr='" + attr + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", inStock=" + inStock +
                ", listPrice=" + listPrice +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
