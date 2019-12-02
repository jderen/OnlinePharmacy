package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.dao.generic.GenericDao;

import java.util.List;

public interface TransactionDao extends GenericDao<Transaction> {
    List<Transaction> getTransactionsByUserId(Long id);
}
