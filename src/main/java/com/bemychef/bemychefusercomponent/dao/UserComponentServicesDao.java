package com.bemychef.bemychefusercomponent.dao;

import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import com.bemychef.bemychefusercomponent.model.UserComponentServices;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserComponentServicesDao {
    List<UserComponentServices> getAllServicesByName(String serviceName);
    List<UserComponentCategory> getAllServicesByNameAndStatus(String serviceName, StatusEnum status);
}
