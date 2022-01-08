package com.maiia.pro.service;

import java.util.List;

import com.maiia.pro.model.PractitionerDTO;

public interface IProPractitionerService {

	PractitionerDTO find(String practitionerId);

	List<PractitionerDTO> findAll();

}