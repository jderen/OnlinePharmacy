package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {
}
