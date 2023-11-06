package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchFormServlet extends HttpServlet {
    private CatalogService catalogService;
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CatalogService catalogService = new CatalogService();
        String keyword = req.getParameter("keyword");
        List<Product> productsList = catalogService.searchProductList(keyword);
        if(keyword.isEmpty()){
            productsList = null;
        }
        HttpSession session = req.getSession();
        session.setAttribute("searchproduct",productsList);
        req.getRequestDispatcher(SEARCH_PRODUCTS).forward(req,resp);
    }
}
