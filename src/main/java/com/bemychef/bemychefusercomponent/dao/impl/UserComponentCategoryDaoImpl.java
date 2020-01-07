package com.bemychef.bemychefusercomponent.dao.impl;

import com.bemychef.bemychefusercomponent.dao.UserComponentCategoryDao;
import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserComponentCategoryDaoImpl implements UserComponentCategoryDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserComponentCategory> getAllServicesByName(String categoryName) {
        Query query = entityManager.createQuery("SELECT ucc FROM UserComponentCategory ucc WHERE ucc.categoryName =:categoryName");
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

    @Override
    public List<UserComponentCategory> getAllServicesByNameAndStatus(String categoryName, StatusEnum status) {
        Query query = entityManager.createQuery("SELECT ucc FROM UserComponentCategory ucc WHERE ucc.categoryName =:categoryName and ucc.status =:status");
        query.setParameter("categoryName", categoryName);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
