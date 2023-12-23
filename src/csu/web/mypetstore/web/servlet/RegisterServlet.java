package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.util.List;

public class RegisterServlet extends HttpServlet {

    private static final String Register_Form = "/WEB-INF/jsp/account/register.jsp";
    private static final String INFORMATION_Form = "/WEB-INF/jsp/account/information.jsp";

    private String username;
    private String password;

    private String email1;

    private String msg;
    private String validationCode;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.validationCode = req.getParameter("validationCode");
        HttpSession session = req.getSession();
        String validation_code = (String) session.getAttribute("authCode");
        session.setAttribute("username" ,username );
        session.setAttribute("password",password);
        if (validationCode.equalsIgnoreCase(validation_code)) {
        if (!validate()) {
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(Register_Form).forward(req, resp);
        } else {
            req.getRequestDispatcher(INFORMATION_Form).forward(req, resp);
        }
    }


    }
        private boolean validate () {
            if (this.username == null || this.username.equals("")) {
                this.msg = "用户名不能为空";
                return false;
            }
            if (this.password == null || this.password.equals("")) {
                this.msg = "密码不能为空";
                return false;
            }
            if (this.email1 == null || this.email1.equals("")) {
                this.msg = "邮箱不能为空";
            }
            return true;
        }

    }
