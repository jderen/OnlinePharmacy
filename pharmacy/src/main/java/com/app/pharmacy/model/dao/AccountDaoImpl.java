package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Account;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDaoImpl extends AbstractGenericDao<Account> implements AccountDao {
    @Override
    public Optional<Long> getIdByLoginAndPassword(String login, String password) {
        if (!login.isEmpty() && !password.isEmpty() && getEntityManager() != null) {
            Query query = getEntityManager()
                    .createQuery("SELECT a.id FROM " + geteClass().getCanonicalName() + " a " +
                            "WHERE a.login = :login AND a.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);

            List queryResult = query.setMaxResults(1).getResultList();
            if (!queryResult.isEmpty()){
                return Optional.of((Long)queryResult.get(0));
            }
        }
        return Optional.empty();
    }

    public Optional<Long> getIdByLogin(String login) {
        if (!login.isEmpty() &&  getEntityManager() != null) {
            Query query = getEntityManager()
                    .createQuery("SELECT a.id FROM " + geteClass().getCanonicalName() + " a " +
                            "WHERE a.login = :login");
            query.setParameter("login", login);

            List queryResult = query.setMaxResults(1).getResultList();
            if (!queryResult.isEmpty()){
                return Optional.of((Long)queryResult.get(0));
            }
        }
        return Optional.empty();
    }
    public String getPasswordById(Long id) {
        String path = "";
        if (getEntityManager() != null) {
            Query query = getEntityManager()
                    .createQuery("SELECT a.password FROM " + geteClass().getCanonicalName() + " a " +
                            "WHERE a.id = :id");
            query.setParameter("id", id);
             path = (String) query.getSingleResult();
        }
        return path;
    }

    @Override
    public boolean isLoginBusy(String login) {
        if (login == null) return false;
        boolean isBusy = false;
        if (!login.isEmpty() && getEntityManager() != null) {
            Query query = getEntityManager().createQuery(
                    "SELECT a.id FROM " + geteClass().getCanonicalName() + " a " + "WHERE a.login = :login");
            query.setParameter("login", login);

            if (!query.setMaxResults(1).getResultList().isEmpty()) isBusy = true;
        }
        return isBusy;
    }
}