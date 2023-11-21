package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartItemServlet extends HttpServlet {

    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/Cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
//        Cart cart = (Cart) session.getAttribute("cart");
        CartService cartService = new CartService();
        Cart cart = (Cart)cartService.getCart(account.getUsername());
        String workingItemId = req.getParameter("workingItemId");
        cartService.removeItemFromCart(cart , workingItemId , account.getUsername());
        Cart cart1 = (Cart)cartService.getCart(account.getUsername());
        session.setAttribute("cart" , cart1);


            req.getRequestDispatcher(CART_FORM).forward(req , resp);

    }
}
