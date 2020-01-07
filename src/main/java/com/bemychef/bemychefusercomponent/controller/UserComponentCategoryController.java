package com.bemychef.bemychefusercomponent.controller;

import com.bemychef.bemychefusercomponent.dto.UserComponentCategoryDTO;
import com.bemychef.bemychefusercomponent.service.UserComponentCategoryService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserComponentCategoryController {

    @Autowired
    private UserComponentCategoryService userComponentCategoryService;

    @PostMapping(path = "/categories", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addCategory(@RequestBody UserComponentCategoryDTO userComponentCategoryDTO) {
        return userComponentCategoryService.addCategories(userComponentCategoryDTO);
    }

    @GetMapping(path = "/categories", produces = "application/json")
    public ResponseEntity<Object> getAllCategoriesList(){
        return userComponentCategoryService.getAllCategories();
    }

    @GetMapping(path = "/categories/id/{categoryId}", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListById(@PathVariable(value = "categoryId") Long categoryId){
        return userComponentCategoryService.getCategoriesById(categoryId);
    }

    @GetMapping(path = "/categories/name/{categoryName}", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListByServiceName(@PathVariable(value = "categoryName") String categoryName){
        return userComponentCategoryService.getCategoriesByCategoryName(categoryName);
    }

    @GetMapping(path = "/categories/name/status/{categoryName}", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListByServiceNameAndStatus(@RequestParam(value = "status") String status, @PathVariable(value = "categoryName") String categoryName){
        return userComponentCategoryService.getCategoriesByCategoryNameAndStatus(categoryName, status);
    }

    @GetMapping(path = "/categories/status", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListByServiceNameAndStatus(@RequestParam(value = "status") String status){
        return userComponentCategoryService.getCategoriesByCategoryStatus(status);
    }
}
