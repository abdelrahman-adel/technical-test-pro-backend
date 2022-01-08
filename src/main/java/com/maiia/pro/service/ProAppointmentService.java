package com.maiia.pro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.mapper.IAppointmentMapper;
import com.maiia.pro.model.AppointmentDTO;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;

@Service
public class ProAppointmentService implements IProAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	@Autowired
	private IAppointmentMapper mapper;

    @Override
	public AppointmentDTO find(String appointmentId) {
        return mapper.mapToDTO(appointmentRepository.findById(appointmentId).orElseThrow());
    }

    @Override
	public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
	public List<AppointmentDTO> findByPractitionerId(Integer practitionerId) {
        return appointmentRepository.findByPractitionerId(practitionerId).stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }
    
	@Override
	public AppointmentDTO create(AppointmentDTO appointment) {
		availabilityRepository.findByPractitionerIdAndStartDateAndEndDate(appointment.getPractitionerId(), appointment.getStartDate(), appointment.getEndDate());
		Optional<Availability> selectedAvailability = availabilityRepository.findByPractitionerIdAndStartDateAndEndDate(
				appointment.getPractitionerId(),
				appointment.getStartDate(),
				appointment.getEndDate());

		AppointmentDTO created;
		if (selectedAvailability.isPresent()) {
			created = mapper.mapToDTO(appointmentRepository.save(mapper.mapToEntity(appointment)));
			availabilityRepository.delete(selectedAvailability.get());
		} else {
			throw new IllegalStateException("This time is not available");
		}
		return created;
	}
}
