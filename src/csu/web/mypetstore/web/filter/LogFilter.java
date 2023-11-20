package csu.web.mypetstore.web.filter;


import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebFilter(filterName = "LogFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "loginPage", value = "/signOnForm"),
                @WebInitParam(name = "neworder", value = "/newOrder"),
                @WebInitParam(name = "confirm", value = "/confirmOrderForm"),
                @WebInitParam(name = "main", value = "/mainForm"),
//                @WebInitParam(name = "signoff", value = "/signOut"),
//                @WebInitParam(name = "edit", value = "/editAccount"),
                @WebInitParam(name = "addtocart", value = "/addItemToCart"),
                @WebInitParam(name = "update", value = "/updateCart"),
               @WebInitParam(name = "shipping", value = "/shippingForm"),
                @WebInitParam(name = "remove", value = "/removeCartItem"),
                @WebInitParam(name = "search", value = "/searchForm")
        })
public class LogFilter implements Filter {
     private String username;
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private FilterConfig config  ;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }
    public void writeDiary(Log log) throws IOException {
        LogService logService = new LogService();
        logService.insertLog(log);
    }


    public void destroy() {
    }
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter coming");
       
        String loginPage = config.getInitParameter("loginPage");
        String main = config.getInitParameter("main");
       String shipping = config.getInitParameter("shipping");
        String search = config.getInitParameter("search");
        String neworder = config.getInitParameter("neworder");
        String addtocart = config.getInitParameter("addtocart");
//       String signoff = config.getInitParameter("signoff");
        String update = config.getInitParameter("update");
//        String edit = config.getInitParameter("edit");
        String remove = config.getInitParameter("remove");
        String confirm = config.getInitParameter("confirm");
        HttpServletRequest req = (HttpServletRequest) Request;
        HttpServletResponse resp = (HttpServletResponse) Response;
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String categoryId = req.getParameter("categoryId");
        String itemId = req.getParameter("itemId");
        String productId = req.getParameter("productId");
        Order order = (Order) session.getAttribute("order");
        String workingItemId = req.getParameter("workingItemId");
        String requestPath = req.getServletPath();
        String orderIdString = req.getParameter("orderId");
            LogService logService = new LogService();
            Log log = new Log();
            String value = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + req.getServletPath() + req.getQueryString();
           username = "游客";
        if(account != null){
            username = account.getUsername();
        }
        log.setUsername(username);
        if(requestPath.endsWith(loginPage)&&username == "游客")
        {
            log.setInfo(username+ "进入登录界面");
            log.setValue(value);
            logService.insertLog(log);
        }

            if (requestPath.endsWith(main)) {
                log.setInfo(username + "游览主界面");
                System.out.println("浏览");
            }
            if (requestPath.endsWith(search)) {
                log.setInfo(username + "搜索商品");
            }
//            if (requestPath.endsWith(signoff)&&account != null) {
//                log.setInfo(username + "退出登录");
//            }
            if (requestPath.endsWith(neworder)&&account != null) {
                log.setInfo(username + "创建订单");
            }
            if (requestPath.endsWith(shipping)&&account != null) {
                log.setInfo(username + "修改地址");
            }
            if (requestPath.endsWith(addtocart)&&account != null) {
                log.setInfo(username + "将商品" + workingItemId + "加入购物车");
            }
            if (requestPath.endsWith(update)&&account != null) {
                log.setInfo(username + "刷新购物车");
            }
            if (requestPath.endsWith(remove)&&account != null) {
                log.setInfo(username + "清除商品" + workingItemId);
            }
//            if (requestPath.endsWith(edit)&&account != null) {
//                log.setInfo(username + "编辑个人信息");
//            }
            if (categoryId != null) {
                log.setInfo(username + "游览" + categoryId);
            }
            if (itemId != null)
                log.setInfo(username + "游览" + itemId);
            if (productId != null) {
                log.setInfo(username + "游览" + productId);
            }
           if (order != null && requestPath.endsWith(confirm)&&account != null) {
                log.setInfo(username + "确认" + "订单");
            }

            if (orderIdString != null&&account != null) {
                log.setInfo(username + "游览" + "订单");
            }
            log.setValue(value);
            logService.insertLog(log);
            if (account == null && (requestPath.endsWith(neworder) || requestPath.endsWith(confirm))) {
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }
            filterChain.doFilter(Request, Response);}
    }
