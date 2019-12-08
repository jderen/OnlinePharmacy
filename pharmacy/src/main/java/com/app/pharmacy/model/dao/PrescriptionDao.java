package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.Prescription;
import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.dao.generic.GenericDao;


public interface PrescriptionDao extends GenericDao<Prescription> {
    String getPhotoPathByPrescriptionId(Long id);
}
