package com.maiia.pro.mapper;

import org.springframework.stereotype.Service;

import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.model.PractitionerDTO;

@Service
public class PractitionerMapper implements IPractitionerMapper {

	@Override
	public PractitionerDTO mapToDTO(Practitioner practitioner) {
		PractitionerDTO practitionerType = new PractitionerDTO();
		practitionerType.setId(practitioner.getId());
		practitionerType.setFirstName(practitioner.getFirstName());
		practitionerType.setLastName(practitioner.getLastName());
		practitionerType.setSpeciality(practitioner.getSpeciality());
		return practitionerType;
	}

}
