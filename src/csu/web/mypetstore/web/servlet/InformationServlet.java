package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InformationServlet extends HttpServlet {
    private static final String Information_Form = "/WEB-INF/jsp/account/information.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;

    private String email;
    private String firstname;

    private String lastname;

    private String status = "OK";
    private String addr1;

    private String addr2;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String phone;

    private String langpref;
    private String favcategory;

    private String msg;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        this.username = (String) session.getAttribute("username");
        this.password = (String) session.getAttribute("password");
        this.email = req.getParameter("account.email");
        this.firstname = req.getParameter("account.firstName");
        this.lastname = req.getParameter("account.lastName");
        this.phone = req.getParameter("account.phone");
        this.addr1 = req.getParameter("account.address1");
        this.addr2 = req.getParameter("account.address2");
        this.city = req.getParameter("account.city");
        this.state = req.getParameter("account.state");
        this.zip = req.getParameter("account.zip");
        this.country = req.getParameter("account.country");
        this.langpref = req.getParameter("account.langpref");
        this.favcategory = req.getParameter("account.favcategory");

        if (!validate()) {
            req.setAttribute("informationMsg", this.msg);
            req.getRequestDispatcher(Information_Form).forward(req, resp);
        } else {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypetstore", "root", "DENGdeng1010");

                String insertQuery = "INSERT INTO account (userid, email, firstname, lastName, status, addr1, addr2, city, state, zip, country, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, firstname);
                preparedStatement.setString(4, lastname);
                preparedStatement.setString(5, status);
                preparedStatement.setString(6, addr1);
                preparedStatement.setString(7, addr2);
                preparedStatement.setString(8, city);
                preparedStatement.setString(9, state);
                preparedStatement.setString(10, zip);
                preparedStatement.setString(11, country);
                preparedStatement.setString(12, phone);

                preparedStatement.executeUpdate();

                preparedStatement.close();

                String insertAnotherTableQuery = "INSERT INTO  signon (username, password) VALUES (?, ?)";
                PreparedStatement preparedStatementAnotherTable = connection.prepareStatement(insertAnotherTableQuery);

                preparedStatementAnotherTable.setString(1, username);  // 替换为目标表的列名和相应的值
                preparedStatementAnotherTable.setString(2, password);

                preparedStatementAnotherTable.executeUpdate();
                preparedStatementAnotherTable.close();

                String insertThirdTableQuery = "INSERT INTO  profile (userid, langpref, favcategory, mylistopt, banneropt) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatementThirdTable = connection.prepareStatement(insertThirdTableQuery);

                preparedStatementThirdTable.setString(1, username);
                preparedStatementThirdTable.setString(2, langpref);
                preparedStatementThirdTable.setString(3, favcategory);
                preparedStatementThirdTable.setString(4, "1");
                preparedStatementThirdTable.setString(5, "1");

                preparedStatementThirdTable.executeUpdate();
                preparedStatementThirdTable.close();

                connection.close();
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace(); // 处理异常，例如记录日志或向用户显示错误消息
            }

        }

        req.setAttribute("username" , username);
        req.setAttribute("password" , password);
    }


    private boolean validate() {
        System.out.println(firstname + " 2");
        if (this.firstname == null || this.firstname.equals("")) {
            this.msg = "firstname不能为空";
            return false;
        }
        if (this.lastname == null || this.lastname.equals("")) {
            this.msg = "lastname不能为空";
            return false;
        }
        if (this.phone == null || this.phone.equals("")) {
            this.msg = "phone不能为空";
            return false;
        }
        if (this.addr1 == null || this.addr1.equals("")) {
            this.msg = "addr1不能为空";
            return false;
        }
        if (this.addr2 == null || this.addr2.equals("")) {
            this.msg = "addr2不能为空";
            return false;
        }
        if (this.city == null || this.city.equals("")) {
            this.msg = "city不能为空";
            return false;
        }
        if (this.state == null || this.state.equals("")) {
            this.msg = "state不能为空";
            return false;
        }
        if (this.zip == null || this.zip.equals("")) {
            this.msg = "zip不能为空";
            return false;
        }
        if (this.country == null || this.country.equals("")) {
            this.msg = "country不能为空";
            return false;
        }
        return true;
    }
}
