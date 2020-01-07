package com.bemychef.bemychefusercomponent.controller;

import com.bemychef.bemychefusercomponent.service.UserComponentServicesService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserComponentServicesController {

    @Autowired
    private UserComponentServicesService userComponentServicesService;

    @PostMapping(path = "/services", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<Object> addServices(@RequestParam("user") String userData, @RequestParam("file") MultipartFile multipartFile) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(userData);
        return userComponentServicesService.addServices(jsonObject, multipartFile);
    }

    @GetMapping(path = "/services", produces = "application/json")
    public ResponseEntity<Object> getAllServicesList(){
        return userComponentServicesService.getAllServices();
    }

    @GetMapping(path = "/services/id/{serviceId}", produces = "application/json")
    public ResponseEntity<Object> getServicesListById(@PathVariable(value = "serviceId") Long serviceId){
        return userComponentServicesService.getServicesById(serviceId);
    }

    @GetMapping(path = "/services/name/{serviceName}", produces = "application/json")
    public ResponseEntity<Object> getServicesListByServiceName(@PathVariable(value = "serviceName") String serviceName){
        return userComponentServicesService.getServicesByServiceName(serviceName);
    }

    @GetMapping(path = "/services/name/status/{serviceName}", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListByServiceNameAndStatus(@RequestParam(value = "status") String status, @PathVariable(value = "serviceName") String serviceName){
        return userComponentServicesService.getServicesByServiceNameAndStatus(serviceName, status);
    }

    @GetMapping(path = "/services/status", produces = "application/json")
    public ResponseEntity<Object> getCategoriesListByServiceStatus(@RequestParam(value = "status") String status){
        return userComponentServicesService.getServicesByServiceStatus(status);
    }
}
