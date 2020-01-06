package com.bemychef.bemychefusercomponent.service;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ServicesService {

    ResponseEntity<Object> getAllServices();

    ResponseEntity<Object> getServicesById(Long serviceId);

    ResponseEntity<Object> getServicesByServiceName(String serviceName);

    ResponseEntity<Object> addServices(JSONObject jsonObject, MultipartFile multipartFile);
}
