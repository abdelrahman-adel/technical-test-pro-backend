package com.maiia.pro.mapper;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.model.AvailabilityDTO;

public interface IAvailabilityMapper {

	AvailabilityDTO mapToDTO(Availability availability);

}