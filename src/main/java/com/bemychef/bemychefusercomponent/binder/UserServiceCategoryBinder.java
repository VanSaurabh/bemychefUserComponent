package com.bemychef.bemychefusercomponent.binder;

import com.bemychef.bemychefusercomponent.dto.UserComponentCategoryDTO;
import com.bemychef.bemychefusercomponent.model.UserComponentCategory;

import javax.inject.Named;

@Named("userServiceCategoryBinder")
public class UserServiceCategoryBinder {

    public UserComponentCategory bindDTOToModel(UserComponentCategoryDTO userServiceCategoryDTO) {
        UserComponentCategory userServiceCategory = new UserComponentCategory();
        userServiceCategory.setStatus(userServiceCategoryDTO.getStatus());
        userServiceCategory.setCategoryName(userServiceCategoryDTO.getCategoryName());
        return userServiceCategory;
    }
}
