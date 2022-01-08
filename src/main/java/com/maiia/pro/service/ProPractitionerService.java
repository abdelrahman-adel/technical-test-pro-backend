package com.maiia.pro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maiia.pro.mapper.IPractitionerMapper;
import com.maiia.pro.model.PractitionerDTO;
import com.maiia.pro.repository.PractitionerRepository;

@Service
public class ProPractitionerService implements IProPractitionerService {

	@Autowired
	private PractitionerRepository practitionerRepository;

	@Autowired
	private IPractitionerMapper mapper;

	@Override
	public PractitionerDTO find(String practitionerId) {
		return mapper.mapToDTO(practitionerRepository.findById(practitionerId).orElseThrow());
	}

	@Override
	public List<PractitionerDTO> findAll() {
		return practitionerRepository.findAll().stream().map(mapper::mapToDTO).collect(Collectors.toList());
	}
}
