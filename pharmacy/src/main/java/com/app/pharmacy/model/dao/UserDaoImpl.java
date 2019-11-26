package com.app.pharmacy.model.dao;

import com.app.pharmacy.model.User;
import com.app.pharmacy.model.dao.generic.AbstractGenericDao;
import com.app.pharmacy.model.enums.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDaoImpl  extends AbstractGenericDao<User> implements UserDao  {

    @Override
    public Role getRole(Long id) {
        Role role = null;
        if(id != null && id >= 0){
            Query query = getEntityManager().createQuery(
                    "SELECT u.role FROM " + geteClass().getCanonicalName() + " u WHERE u.id = :id"
            );
            query.setParameter("id", id);
            try {
                role = (Role) query.getSingleResult();
            }
            catch (NoResultException ignored){}
        }
        return role;
    }


}