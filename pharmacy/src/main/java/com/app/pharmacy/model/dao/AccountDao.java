package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.dao.generic.GenericDao;
import com.app.pharmacy.model.Account;

import java.util.Optional;

public interface AccountDao extends GenericDao<Account> {
    Optional<Long> getIdByLoginAndPassword(String login, String password);
    Optional<Long> getIdByLogin(String login);
    String getPasswordById(Long id);
    boolean isLoginBusy(String login);
}