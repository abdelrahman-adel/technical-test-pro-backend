package com.maiia.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiia.pro.model.PatientDTO;
import com.maiia.pro.service.IProPatientService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProPatientController {
    @Autowired
    private IProPatientService proPatientService;

    @ApiOperation(value = "Get patients")
    @GetMapping
    public List<PatientDTO> getPatients() {
        return proPatientService.findAll();
    }
}
