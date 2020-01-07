package com.bemychef.bemychefusercomponent.service;

import com.bemychef.bemychefusercomponent.dto.UserComponentCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserComponentCategoryService {
    ResponseEntity<Object> getAllCategories();

    ResponseEntity<Object> getCategoriesById(Long serviceId);

    ResponseEntity<Object> getCategoriesByCategoryName(String serviceName);

    ResponseEntity<Object> getCategoriesByCategoryNameAndStatus(String categoryName, String status);

    ResponseEntity<Object> addCategories(UserComponentCategoryDTO userServiceCategoryDTO);

    ResponseEntity<Object> getCategoriesByCategoryStatus(String status);
}
