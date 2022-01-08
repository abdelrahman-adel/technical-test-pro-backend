package com.maiia.pro.mapper;

import org.springframework.stereotype.Service;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.model.AvailabilityDTO;

@Service
public class AvailabilityMapper implements IAvailabilityMapper {

	@Override
	public AvailabilityDTO mapToDTO(Availability availability) {
		AvailabilityDTO availabilityType = new AvailabilityDTO();
		availabilityType.setId(availability.getId());
		availabilityType.setPractitionerId(availability.getPractitionerId());
		availabilityType.setStartDate(availability.getStartDate());
		availabilityType.setEndDate(availability.getEndDate());
		return availabilityType;
	}

}
