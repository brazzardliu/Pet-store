package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSONObject;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;


import java.io.IOException;
import java.sql.*;
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

public class TestServlet extends HttpServlet{
    private static final String Register_Form = "/WEB-INF/jsp/account/register.jsp";
    private String username;
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        username = req.getParameter("username");
        try {

            // 在这里进行数据库操作
            // 例如调用 checkUserExists 方法
            boolean userExists = checkUserExists(username);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("exist" , userExists);
            // 可以在这里使用数据库返回的结果进行处理或者响应
            if (userExists) {
                response.getWriter().write("true");
            } else {
                response.getWriter().println("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 在发生异常时进行错误处理
            response.getWriter().println("发生异常：" + e.getMessage());
        }
    }

    private boolean checkUserExists(String username) {
        String sql = "SELECT * FROM ACCOUNT WHERE USERID = ?";
        boolean exist=false;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                    exist=true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(statement);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exist;
    }
}



