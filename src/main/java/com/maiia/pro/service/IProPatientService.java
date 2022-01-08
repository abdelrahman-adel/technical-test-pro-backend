package com.maiia.pro.service;

import java.util.List;

import com.maiia.pro.model.PatientDTO;

public interface IProPatientService {

	PatientDTO find(String patientId);

	List<PatientDTO> findAll();

}