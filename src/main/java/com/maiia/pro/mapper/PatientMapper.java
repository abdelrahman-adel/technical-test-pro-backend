package com.maiia.pro.mapper;

import org.springframework.stereotype.Service;

import com.maiia.pro.entity.Patient;
import com.maiia.pro.model.PatientDTO;

@Service
public class PatientMapper implements IPatientMapper {

	@Override
	public PatientDTO mapToDTO(Patient patient) {
		PatientDTO patientType = new PatientDTO();
		patientType.setId(patient.getId());
		patientType.setFirstName(patient.getFirstName());
		patientType.setLastName(patient.getLastName());
		patientType.setBirthDate(patient.getBirthDate());
		return patientType;
	}

}
