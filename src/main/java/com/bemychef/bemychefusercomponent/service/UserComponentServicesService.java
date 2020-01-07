package com.bemychef.bemychefusercomponent.service;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserComponentServicesService {

    ResponseEntity<Object> getAllServices();

    ResponseEntity<Object> getServicesById(Long serviceId);

    ResponseEntity<Object> getServicesByServiceName(String serviceName);

    ResponseEntity<Object> addServices(JSONObject jsonObject, MultipartFile multipartFile);

    ResponseEntity<Object> getServicesByServiceNameAndStatus(String categoryName, String status);

    ResponseEntity<Object> getServicesByServiceStatus(String status);
}
