package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.LineItem;

import java.util.List;

public interface CartDao {
    List<CartItem> getCartItemByUserId(String userId);

    void insertCartItem(Cart cart);
    void removeCartItem(String userId);
    void removeCartItemById(String userId , String itemId);
     void updateQuantity(String itemId , int quantity , String userId);

    Cart getCart(String userId);

}
