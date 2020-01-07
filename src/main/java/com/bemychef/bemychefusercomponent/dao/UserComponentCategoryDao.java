package com.bemychef.bemychefusercomponent.dao;

import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserComponentCategoryDao {
    List<UserComponentCategory> getAllServicesByName(String serviceName);

    List<UserComponentCategory> getAllServicesByNameAndStatus(String categoryName, StatusEnum status);
}
