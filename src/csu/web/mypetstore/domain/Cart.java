package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 8329559983943337176L;
    private String userId;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private List<CartItem> itemList = new ArrayList<CartItem>();

    public List<CartItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }
    public void setCartItemList(List<CartItem> itemList){
        this.itemList = itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock , String userId) {
        CartItem cartItem = new CartItem();
        boolean isExist = false;
        for (int i = 0 ; i < this.getCartItemList().size() ; i++){
            if (Objects.equals(this.getCartItemList().get(i).getItemId() , item.getItemId())){
                int quantity = this.getCartItemList().get(i).getQuantity();
                this.getCartItemList().get(i).setQuantity(quantity + 1);
                isExist = true;
                break;
            }

        }
        if (!isExist){
            cartItem.setItem(item);
            cartItem.setQuantity(1);
            cartItem.setInStock(isInStock);
            cartItem.setUserId(userId);
            cartItem.setItemId(item.getItemId());
            cartItem.setAttr(item.getAttribute1());
            cartItem.setListPrice(item.getListPrice());
            cartItem.setProductId(item.getProduct().getProductId());
            cartItem.setUnitPrice(item.getUnitCost());
            cartItem.setUserId(userId);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }


    }

    public void removeItemById(String itemId , Cart cart) {
        for (int i = 0 ; i < cart.getCartItemList().size() ; i ++ ){
            if (Objects.equals(cart.getCartItemList().get(i).getItemId() , itemId)){
                cart.itemList.remove(i);
            }
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.incrementQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        for (int i = 0 ; i < this.getCartItemList().size() ; i ++){
            if (Objects.equals(this.getCartItemList().get(i).getItemId(), itemId)){
                this.getCartItemList().get(i).setQuantity(quantity);
            }
        }
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }
    public String getUserId(){return userId;}
    public void setUserId(String userId){this.userId = userId;}

    @Override
    public String toString() {
        return "Cart{" +
                "userId='" + userId + '\'' +
                ", itemMap=" + itemMap +
                ", itemList=" + itemList +
                '}';
    }
}
