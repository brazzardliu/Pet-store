package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(){
        this.accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
}