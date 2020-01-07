package com.bemychef.bemychefusercomponent.dao.impl;

import com.bemychef.bemychefusercomponent.dao.UserComponentServicesDao;
import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import com.bemychef.bemychefusercomponent.model.UserComponentServices;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserComponentServiceDaoImpl implements UserComponentServicesDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserComponentServices> getAllServicesByName(String serviceName) {
        Query query = entityManager.createQuery("SELECT ucs FROM UserComponentServices ucs WHERE ucs.name =:serviceName");
        query.setParameter("serviceName", serviceName);
        return query.getResultList();
    }

    @Override
    public List<UserComponentCategory> getAllServicesByNameAndStatus(String serviceName, StatusEnum status) {
        Query query = entityManager.createQuery("SELECT ucs FROM UserComponentServices ucs WHERE ucs.name =:serviceName and ucs.status =:status");
        query.setParameter("serviceName", serviceName);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
