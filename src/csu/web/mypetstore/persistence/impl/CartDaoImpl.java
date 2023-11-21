package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.*;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    private static final String getCartItemByUserIdString = "SELECT " +
            "USERID , lINENUM AS lineNumber , ITEMID , PRODUCTID , ATTR , ISINSTOCK AS inStock , QUANTITY , LISTPRICE , UNITPRICE FROM CARTITEM" +
            " WHERE USERID = ? ";

    private static final String insertCartItemString = " INSERT INTO CARTITEM " +
            "(USERID , LINENUM , ITEMID , PRODUCTID , ATTR , ISINSTOCK , QUANTITY , LISTPRICE , UNITPRICE) VALUES (? ,? ,? ,?, ?, ?, ?, ?, ?)";

    private static final String removeCartItemString = "DELETE FROM CARTITEM WHERE USERID = ?";
    private static final String removeCartItemByIdString = "DELETE FROM CARTITEM WHERE USERID = ? AND ITEMID = ?";
    private static final String updateQuantityString = "UPDATE CARTITEM SET QUANTITY = ? WHERE USERID = ? AND ITEMID = ?";

    @Override
    public List<CartItem> getCartItemByUserId(String userId) {
        List<CartItem> cartItemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartItemByUserIdString);
            preparedStatement.setString(1 , userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CartItem cartItem = new CartItem();

                cartItem.setUserId(resultSet.getString(1));
                cartItem.setLineNumber(resultSet.getInt(2));
                cartItem.setItemId(resultSet.getString(3));
                cartItem.setProductId(resultSet.getString(4));
                cartItem.setAttr(resultSet.getString(5));
                cartItem.setInStock(resultSet.getBoolean(6));
                cartItem.setQuantity(resultSet.getInt(7));
                cartItem.setListPrice(resultSet.getBigDecimal(8));
                cartItem.setUnitPrice(resultSet.getBigDecimal(9));

                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItemList;
    }



    @Override
    public void insertCartItem(Cart cart) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertCartItemString);
            for(int i = 0; i < cart.getNumberOfItems(); i ++){
                preparedStatement.setString(1, cart.getCartItemList().get(i).getUserId());
                preparedStatement.setInt(2, cart.getCartItemList().get(i).getLineNumber());
                preparedStatement.setString(3, cart.getCartItemList().get(i).getItemId());
                preparedStatement.setString(4, cart.getCartItemList().get(i).getItem().getProductId());
                preparedStatement.setString(5, cart.getCartItemList().get(i).getAttr());
                preparedStatement.setBoolean(6, cart.getCartItemList().get(i).isInStock());
                preparedStatement.setInt(7, cart.getCartItemList().get(i).getQuantity());
                preparedStatement.setBigDecimal(8, cart.getCartItemList().get(i).getListPrice());
                preparedStatement.setBigDecimal(9, cart.getCartItemList().get(i).getUnitPrice());

            }

            preparedStatement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeCartItem(String userId) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeCartItemString);
            preparedStatement.setString(1 , userId);
            preparedStatement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Cart getCart(String userId) {
        Cart cart = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartItemByUserIdString);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                CartItem cartItem = new CartItem();

                cartItem.setUserId(resultSet.getString(1));
                cartItem.setLineNumber(resultSet.getInt(2));
                cartItem.setItemId(resultSet.getString(3));
                cartItem.setProductId(resultSet.getString(4));
                cartItem.setAttr(resultSet.getString(5));
                cartItem.setInStock(resultSet.getBoolean(6));
                cartItem.setQuantity(resultSet.getInt(7));
                cartItem.setListPrice(resultSet.getBigDecimal(8));
                cartItem.setUnitPrice(resultSet.getBigDecimal(9));

            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void removeCartItemById(String userId , String itemId){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeCartItemByIdString);
            preparedStatement.setString(1 , userId);
            preparedStatement.setString(2 , itemId);
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuantity(String itemId, int quantity, String userId) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuantityString);
            preparedStatement.setInt(1 , quantity);
            preparedStatement.setString(2 , userId);
            preparedStatement.setString(3 , itemId);
            preparedStatement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
