package com.maiia.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiia.pro.model.PractitionerDTO;
import com.maiia.pro.service.IProPractitionerService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/practitioners", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProPractitionerController {
    @Autowired
    private IProPractitionerService proPractitionerService;

    @ApiOperation(value = "Get practitioners")
    @GetMapping
    public List<PractitionerDTO> getPractitioners() {
        return proPractitionerService.findAll();
    }
}
