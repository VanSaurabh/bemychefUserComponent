package com.bemychef.bemychefusercomponent.dao.impl;

import com.bemychef.bemychefusercomponent.dao.UserServicesDao;
import com.bemychef.bemychefusercomponent.model.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserServiceDaoImpl implements UserServicesDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserServices> getAllServicesByName(String serviceName) {
        Query query = entityManager.createQuery("SELECT services FROM UserServices us WHERE us.name =:serviceName");
        query.setParameter("serviceName", serviceName);
        return query.getResultList();
    }
}
