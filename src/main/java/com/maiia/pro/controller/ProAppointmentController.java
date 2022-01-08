package com.maiia.pro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiia.pro.model.AppointmentDTO;
import com.maiia.pro.service.IProAppointmentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAppointmentController {

    @Autowired
    private IProAppointmentService proAppointmentService;

    @ApiOperation(value = "Get appointments by practitionerId")
    @GetMapping("/{practitionerId}")
    public List<AppointmentDTO> getAppointmentsByPractitioner(@PathVariable final String practitionerId) {
        return proAppointmentService.findByPractitionerId(Integer.parseInt(practitionerId));
    }

    @ApiOperation(value = "Get all appointments")
    @GetMapping
    public List<AppointmentDTO> getAppointments() {
        return proAppointmentService.findAll();
    }

    @ApiOperation(value = "Create appointment")
    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody @Valid AppointmentDTO appointment) {
        return proAppointmentService.create(appointment);
    }
}
