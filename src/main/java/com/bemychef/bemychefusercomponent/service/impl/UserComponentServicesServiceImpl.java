package com.bemychef.bemychefusercomponent.service.impl;

import com.bemychef.bemychefusercomponent.dao.UserComponentServicesDao;
import com.bemychef.bemychefusercomponent.dao.UserComponentServicesRepo;
import com.bemychef.bemychefusercomponent.model.UserComponentServices;
import com.bemychef.bemychefusercomponent.service.UserComponentServicesService;
import com.bemychef.bemychefusercomponent.util.ExceptionUtil;
import com.bemychef.modelComponent.commonEnum.StatusEnum;
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

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@PropertySource(value = "file:C:\\Users\\saura\\Downloads\\PROPERTIES\\FileLocation.properties")
@Service
public class UserComponentServicesServiceImpl implements UserComponentServicesService {

    @Autowired
    private UserComponentServicesRepo userComponentServicesRepo;
    @Autowired
    private UserComponentServicesDao userComponentServicesDao;
    @Value("${location}")
    private String pathToStoreFiles;

    private static final Logger logger = LoggerFactory.getLogger(UserComponentServicesServiceImpl.class);

    @Override
    public ResponseEntity<Object> getAllServices() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentServicesRepo.findAll());
        } catch (Exception ex) {
            logger.error("Got exception while getAllServices operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getServicesById(Long serviceId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentServicesRepo.findById(serviceId));
        } catch (Exception ex) {
            logger.error("Got exception while getServicesById operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getServicesByServiceName(String serviceName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentServicesDao.getAllServicesByName(serviceName));
        } catch (Exception ex) {
            logger.error("Got exception while getServicesByServiceName operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
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
            return ExceptionUtil.commonErrorResponse();
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userComponentServicesRepo.save(preparePersistentUserEntity(path, jsonObject)));
        } catch (Exception ex) {
            logger.error("Got exception while addServices operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    @Override
    public ResponseEntity<Object> getServicesByServiceNameAndStatus(String serviceName, String status) {
        StatusEnum statusEnum = StatusEnum.ACTIVE;
        try{
            if(status.equalsIgnoreCase("InActive")){
                statusEnum = StatusEnum.INACTIVE;
            }else if(status.equalsIgnoreCase("Deleted")){
                statusEnum = StatusEnum.DELETED;
            }
            return ResponseEntity.status(HttpStatus.OK).body(userComponentServicesDao.getAllServicesByNameAndStatus(serviceName, statusEnum));
        }catch (Exception ex){
            logger.error("Got exception while getServicesById operation : " + ex.toString());
            return ExceptionUtil.commonErrorResponse();
        }
    }

    private UserComponentServices preparePersistentUserEntity(String filePath, JSONObject jsonObject) {
        UserComponentServices userComponentServices = new UserComponentServices();
        userComponentServices.setImageLocation(filePath);
        userComponentServices.setName(jsonObject.get("name").toString());
        if(jsonObject.get("status").toString().equalsIgnoreCase("Active"))
            userComponentServices.setStatus(StatusEnum.ACTIVE);
        else if(jsonObject.get("status").toString().equalsIgnoreCase("Inactive"))
            userComponentServices.setStatus(StatusEnum.INACTIVE);
        else if(jsonObject.get("status").toString().equalsIgnoreCase("Deleted"))
            userComponentServices.setStatus(StatusEnum.DELETED);
        return userComponentServices;
    }

    @Override
    public ResponseEntity<Object> getServicesByServiceStatus(String status) {
        List<UserComponentServices> UserComponentServicesList = (List<UserComponentServices>) userComponentServicesRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(UserComponentServicesList.stream().filter(userComponentCategory -> userComponentCategory.getStatus().toString().equalsIgnoreCase(status)).collect(Collectors.toList()));
    }

}
