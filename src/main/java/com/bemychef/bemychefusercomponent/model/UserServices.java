package com.bemychef.bemychefusercomponent.model;

import com.bemychef.bemychefusercomponent.enums.ServiceStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "bemychef_services")
public class UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageLocation;
    @Enumerated(EnumType.STRING)
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


    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

}
