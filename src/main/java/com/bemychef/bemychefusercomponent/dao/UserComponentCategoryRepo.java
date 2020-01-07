package com.bemychef.bemychefusercomponent.dao;

import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComponentCategoryRepo extends CrudRepository<UserComponentCategory, Long> {
}
