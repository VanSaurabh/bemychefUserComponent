package com.bemychef.bemychefusercomponent.dto;

import com.bemychef.modelComponent.commonEnum.StatusEnum;

public class UserComponentCategoryDTO {

    private String categoryName;
    private StatusEnum status;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}