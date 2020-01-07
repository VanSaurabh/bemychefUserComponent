package com.bemychef.bemychefusercomponent.dao;

import com.bemychef.bemychefusercomponent.model.UserComponentServices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComponentServicesRepo extends CrudRepository<UserComponentServices, Long> {
}
