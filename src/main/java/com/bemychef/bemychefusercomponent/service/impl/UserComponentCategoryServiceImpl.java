package com.bemychef.bemychefusercomponent.service.impl;

import com.bemychef.bemychefusercomponent.binder.UserServiceCategoryBinder;
import com.bemychef.bemychefusercomponent.dao.UserComponentCategoryDao;
import com.bemychef.bemychefusercomponent.dao.UserComponentCategoryRepo;
import com.bemychef.bemychefusercomponent.dto.UserComponentCategoryDTO;
import com.bemychef.bemychefusercomponent.model.UserComponentCategory;
import com.bemychef.bemychefusercomponent.service.UserComponentCategoryService;
import com.bemychef.bemychefusercomponent.util.ExceptionUtil;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserComponentCategoryServiceImpl implements UserComponentCategoryService {

    @Autowired
    private UserComponentCategoryDao userComponentCategoryDao;
    @Autowired
    private UserComponentCategoryRepo userComponentCategoryRepo;
    @Autowired
    private UserServiceCategoryBinder userServiceCategoryBinder;

    private static final Logger logger = LoggerFactory.getLogger(UserComponentCategoryServiceImpl.class);

    @Override
    public ResponseEntity<Object> getAllCategories() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryRepo.findAll());
        }catch (Exception ex){
            logger.error("Got exception while getAllServices operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getCategoriesById(Long categoryId) {
       try{
           return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryRepo.findById(categoryId));
       }catch (Exception ex){
           logger.error("Got exception while getServicesById operation : " + ex.toString());
           return ExceptionUtil.commonErrorResponse();
       }
    }

    @Override
    public ResponseEntity<Object> getCategoriesByCategoryName(String categoryName) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryDao.getAllServicesByName(categoryName));
        }catch (Exception ex){
            logger.error("Got exception while getServicesByServiceName operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getCategoriesByCategoryNameAndStatus(String categoryName, String status) {
        StatusEnum statusEnum = StatusEnum.ACTIVE;
        try{
            if(status.equalsIgnoreCase("InActive")){
                statusEnum = StatusEnum.INACTIVE;
            }else if(status.equalsIgnoreCase("Deleted")){
                statusEnum = StatusEnum.DELETED;
            }
            return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryDao.getAllServicesByNameAndStatus(categoryName, statusEnum));
        }catch (Exception ex){
            logger.error("Got exception while getServicesByServiceNameAndStatus operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> addCategories(UserComponentCategoryDTO userServiceCategoryDTO) {
        UserComponentCategory userComponentCategory = userServiceCategoryBinder.bindDTOToModel(userServiceCategoryDTO);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryRepo.save(userComponentCategory));
        }catch (Exception ex){
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getCategoriesByCategoryStatus(String status) {
        List<UserComponentCategory> userComponentCategoryList = (List<UserComponentCategory>) userComponentCategoryRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userComponentCategoryList.stream().filter(userComponentCategory -> userComponentCategory.getStatus().toString().equalsIgnoreCase(status)).collect(Collectors.toList()));
    }
}
