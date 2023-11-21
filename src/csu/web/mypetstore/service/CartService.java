package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.*;
import csu.web.mypetstore.persistence.*;
import csu.web.mypetstore.persistence.impl.*;
import org.apache.catalina.ha.session.DeltaSession;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartService {
    private ItemDao itemDao;
    private CartDao cartDao;
    private AccountDao accountDao;
    private ProductDao productDao;
    private SequenceDao sequenceDao;

    public CartService(){
        itemDao = new ItemDaoImpl();
        cartDao = new CartDaoImpl();
        accountDao = new AccountDaoImpl();
        productDao = new ProductDaoImpl();
        sequenceDao = new SequenceDaoImpl();
    }

    public void insertCart(Cart cart , String userId) {
        cart.setUserId(userId);
        for (int i = 0; i < cart.getNumberOfItems(); i++) {
            CartItem cartItem = (CartItem) cart.getCartItemList().get(i);
            String itemId = cartItem.getItemId();
            Integer increment = cartItem.getQuantity();
//            Map<String, Object> param = new HashMap<String, Object>(2);
//            param.put("itemId", itemId);
//            param.put("increment", increment);
            itemDao.updateInventoryQuantity(itemId , increment);
        }

        cartDao.insertCartItem(cart);
        for (int i = 0; i < cart.getNumberOfItems(); i++) {
            CartItem cartItem = (CartItem) cart.getCartItemList().get(i);
            cartItem.setUserId(cart.getUserId());
            cartDao.insertCartItem(cart);
        }
    }

    public Cart getCart(String userId) {
        Cart cart = new Cart();
        if(cartDao != null) {
            cart.setCartItemList(cartDao.getCartItemByUserId(userId));
//            cart = cartDao.getCart(userId);
//            System.out.println(cart.getItemList().toString());
        }

        for (int i = 0; i < cart.getNumberOfItems(); i++) {
            CartItem cartItem = (CartItem) cart.getCartItemList().get(i);
            Item item = itemDao.getItem(cartItem.getItemId());
            item.setQuantity(itemDao.getInventoryQuantity(cartItem.getItemId()));
            cart.getItemList().get(i).setItem(item);

        }
        cart.setUserId(userId);
        return cart;
    }

    public List<CartItem> getCartItemByUsername(String username) {
        return cartDao.getCartItemByUserId(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceDao.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDao.updateSequence(parameterObject);
        return sequence.getNextId();
    }

    public Cart removeItemFromCart(Cart cart , String itemId , String userId){
        cart.removeItemById(itemId , cart);
        cartDao.removeCartItemById(userId , itemId);
        return cart;
    }
    public Cart addItemToCart(Cart cart , String itemId , String userId){
        Item item = itemDao.getItem(itemId);
        cart = cart.addItem(item , true , userId , cart);
        cartDao.removeCartItem(userId);
        cartDao.insertCartItem(cart);
        return cart;
    }

    public void updateQuantity(String itemId , int quantity , String userId){
        Cart cart = cartDao.getCart(userId);
        cart.setQuantityByItemId(itemId , quantity , cart);
        cartDao.updateQuantity(itemId , quantity , userId);
    }
}
