package csu.web.mypetstore.persistence.impl;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogDaoImpl implements LogDao {
    private static final String INSERT_LOG=
            "INSERT INTO log (username,info,value,data)VALUES(?,?,?,NOW())";
    @Override
    public void insertLog(Log log) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
            preparedStatement.setString(1,log.getUsername());
            preparedStatement.setString(2,log.getInfo());
            preparedStatement.setString(3,log.getValue());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
