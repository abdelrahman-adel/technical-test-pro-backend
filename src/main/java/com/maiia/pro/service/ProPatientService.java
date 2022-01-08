package com.maiia.pro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maiia.pro.mapper.IPatientMapper;
import com.maiia.pro.model.PatientDTO;
import com.maiia.pro.repository.PatientRepository;

@Service
public class ProPatientService implements IProPatientService {
	
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private IPatientMapper mapper;

    @Override
	public PatientDTO find(String patientId) {
        return mapper.mapToDTO(patientRepository.findById(patientId).orElseThrow());
    }

    @Override
	public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }
}
