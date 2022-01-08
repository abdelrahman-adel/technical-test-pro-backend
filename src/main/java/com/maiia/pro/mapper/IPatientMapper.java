package com.maiia.pro.mapper;

import com.maiia.pro.entity.Patient;
import com.maiia.pro.model.PatientDTO;

public interface IPatientMapper {

	PatientDTO mapToDTO(Patient patient);

}