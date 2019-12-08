package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Prescription;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class PrescriptionDaoImpl extends AbstractGenericDao<Prescription> implements PrescriptionDao {
    @Override
    public String getPhotoPathByPrescriptionId(Long id) {
        String path = "";
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT p.photo FROM " + geteClass().getCanonicalName() + " p WHERE p.id = :id"
            );
            query.setParameter("id", id);
            path = (String) query.getSingleResult();
        }
        return path;
    }
}
