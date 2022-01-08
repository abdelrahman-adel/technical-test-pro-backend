package com.maiia.pro.service;

import java.util.List;

import com.maiia.pro.model.AvailabilityDTO;

public interface IProAvailabilityService {

	List<AvailabilityDTO> findByPractitionerId(Integer practitionerId);

	List<AvailabilityDTO> generateAvailabilities(Integer practitionerId);

}