package com.bemychef.bemychefusercomponent.dto;

import com.bemychef.bemychefusercomponent.enums.ServiceStatus;

public class UserServicesDTO {

    private String name;
    private ServiceStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}
