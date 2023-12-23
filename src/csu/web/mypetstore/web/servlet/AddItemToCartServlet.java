package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;
    private Cart cart;             //购物车

    //3.是否需要调用业务逻辑层
    private CatalogService catalogService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        Account account;
        //从数据库中，获取购物车
        HttpSession session = req.getSession();
        CartService cartService = new CartService();
        account = (Account) session.getAttribute("account");


        if (account != null) {
            if (cartService.getCart(account.getUsername()) == null) {
                //第一次使用购物车
                cart = new Cart();

            }
            cart = cartService.getCart(account.getUsername());
            cartService.addItemToCart(cart , workingItemId , account.getUsername());

            session.setAttribute("cart" , cart);



                HttpServletRequest httpRequest = (HttpServletRequest) req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());


                req.getRequestDispatcher(CART_FORM).forward(req, resp);
            }
        }
    }
