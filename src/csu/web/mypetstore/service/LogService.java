package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;

import java.util.List;

public class LogService {

    private LogDao logDao;

    public LogService(){
        logDao=new LogDaoImpl();
    }
    public void insertLog(Log log)
    {
        logDao.insertLog(log);
    }


}
