package com.bemychef.bemychefusercomponent.controller;

import com.bemychef.bemychefusercomponent.service.ServicesService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping(path = "/services", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<Object> addServices(@RequestParam("user") String userData, @RequestParam("file") MultipartFile multipartFile) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(userData);
        return servicesService.addServices(jsonObject, multipartFile);
    }

    @GetMapping(path = "/services", produces = "application/json")
    public ResponseEntity<Object> getAllServicesList(){
        return servicesService.getAllServices();
    }

    @GetMapping(path = "/services/{serviceId}", produces = "application/json")
    public ResponseEntity<Object> getServicesListById(@PathVariable(value = "serviceId") Long serviceId){
        return servicesService.getServicesById(serviceId);
    }

    @GetMapping(path = "/services/{name}", produces = "application/json")
    public ResponseEntity<Object> getServicesListByServiceName(@PathVariable(value = "serviceName") String serviceName){
        return servicesService.getServicesByServiceName(serviceName);
    }
}
