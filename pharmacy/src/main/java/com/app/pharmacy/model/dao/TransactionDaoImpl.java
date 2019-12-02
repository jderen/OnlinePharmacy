package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDaoImpl extends AbstractGenericDao<Transaction> implements TransactionDao {

    public List<Transaction> getTransactionsByUserId(Long id) {
        List<Transaction> list = new ArrayList<>();
        if (getEntityManager() != null) {
            Query query = getEntityManager().createQuery(
                    "SELECT t FROM " + geteClass().getCanonicalName() +
                            " t JOIN com.app.pharmacy.model.User u " +
                            "ON u.id = :id " +
                            "WHERE t.user = :id"
            );
            query.setParameter("id", id);
            list = query.getResultList();
        }
        return list;
    }
}
