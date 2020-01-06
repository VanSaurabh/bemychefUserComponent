package com.bemychef.bemychefusercomponent.service.impl;

import com.bemychef.bemychefusercomponent.dao.UserServicesDao;
import com.bemychef.bemychefusercomponent.dao.UserServicesRepo;
import com.bemychef.bemychefusercomponent.dto.UserServicesDTO;
import com.bemychef.bemychefusercomponent.enums.ErrorMessageEnum;
import com.bemychef.bemychefusercomponent.enums.ServiceStatus;
import com.bemychef.bemychefusercomponent.model.UserServices;
import com.bemychef.bemychefusercomponent.service.ServicesService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@PropertySource(value = "file:C:\\Users\\saura\\Downloads\\PROPERTIES\\FileLocation.properties")
@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private UserServicesRepo userServicesRepo;
    @Autowired
    private UserServicesDao userServicesDao;
    @Value("${location}")
    private String pathToStoreFiles;

    private static final Logger logger = LoggerFactory.getLogger(ServicesServiceImpl.class);

    @Override
    public ResponseEntity<Object> getAllServices() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServicesRepo.findAll());
        } catch (Exception ex) {
            logger.error("Got exception while getAllServices operation : " + ex.toString());
            return commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getServicesById(Long serviceId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServicesRepo.findById(serviceId));
        } catch (Exception ex) {
            logger.error("Got exception while getServicesById operation : " + ex.toString());
            return commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getServicesByServiceName(String serviceName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServicesDao.getAllServicesByName(serviceName));
        } catch (Exception ex) {
            logger.error("Got exception while getServicesByServiceName operation : " + ex.toString());
            return commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> addServices(JSONObject jsonObject, MultipartFile multipartFile) {
        String path = null;
        try {
            String fileName = multipartFile.getOriginalFilename();
             path = pathToStoreFiles+fileName;
            multipartFile.transferTo(new File(path));
        } catch (Exception ex) {
            logger.error("Got exception while addServices operation to parse the file : " + ex.toString());
            return commonErrorResponse();
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServicesRepo.save(preparePersistentUserEntity(path, jsonObject)));
        } catch (Exception ex) {
            logger.error("Got exception while addServices operation : " + ex.toString());
            return commonErrorResponse();
        }
    }

    private UserServices preparePersistentUserEntity(String filePath, JSONObject jsonObject) {
        UserServices userServices = new UserServices();
        userServices.setImageLocation(filePath);
        userServices.setName(jsonObject.get("name").toString());
        if(jsonObject.get("status").toString().equalsIgnoreCase("Active"))
            userServices.setStatus(ServiceStatus.ACTIVE);
        else if(jsonObject.get("status").toString().equalsIgnoreCase("Inactive"))
            userServices.setStatus(ServiceStatus.INACTIVE);
        else if(jsonObject.get("status").toString().equalsIgnoreCase("Deleted"))
            userServices.setStatus(ServiceStatus.DELETED);
        return userServices;
    }

    private ResponseEntity<Object> commonErrorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessageEnum.CONTACT_ADMIN);
    }
}
