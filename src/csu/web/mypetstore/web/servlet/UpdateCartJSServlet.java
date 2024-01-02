package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateCartJSServlet extends HttpServlet {
    //private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //private String workingItemId;
    private Cart cart;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        CartService cartService = new CartService();
        String quantity = req.getParameter("quantity");
        String itemId = req.getParameter("itemid");

        cartService.updateQuantity(itemId , Integer.parseInt(quantity) , account.getUsername());

        cart = cartService.getCart(account.getUsername());
        session.setAttribute("cart" , cart);
    }
}
