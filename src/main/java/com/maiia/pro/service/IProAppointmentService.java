package com.maiia.pro.service;

import java.util.List;

import com.maiia.pro.model.AppointmentDTO;

public interface IProAppointmentService {

	AppointmentDTO find(String appointmentId);

	List<AppointmentDTO> findAll();

	List<AppointmentDTO> findByPractitionerId(Integer practitionerId);

	AppointmentDTO create(AppointmentDTO appointment);

}