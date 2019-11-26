package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.User;
import com.app.pharmacy.model.dao.generic.GenericDao;
import com.app.pharmacy.model.enums.Role;


public interface UserDao extends GenericDao<User> {
    Role getRole(Long id);
}