package com.bemychef.bemychefusercomponent.dao;

import com.bemychef.bemychefusercomponent.model.UserServices;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServicesDao {
    List<UserServices> getAllServicesByName(String serviceName);
}
