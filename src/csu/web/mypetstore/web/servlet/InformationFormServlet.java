package csu.web.mypetstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InformationFormServlet extends HttpServlet {

    private static final String INFORMATION_Form = "/WEB-INF/jsp/account/information.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
//        content = "<script>alert(/xss/)</script>";
        String content = request.getParameter("content");
//        System.out.println(content);
        request.setAttribute("content", content);
        request.getRequestDispatcher("index1.jsp").forward(request, response);
    }


}
