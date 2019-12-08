package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {
    @Override
    public String getImagePathByProductId(Long id) {
        String path = "";
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT p.image FROM " + geteClass().getCanonicalName() + " p WHERE p.id = :id"
            );
            query.setParameter("id", id);
            path = (String) query.getSingleResult();
        }
        return path;
    }
}
