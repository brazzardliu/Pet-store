package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    private static final String getSequenceString = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String updateSequenceString = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence sequence1 = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSequenceString);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sequence1 = new Sequence();
                sequence1.setName(resultSet.getString(1));
                sequence1.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sequence1;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSequenceString);
            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
