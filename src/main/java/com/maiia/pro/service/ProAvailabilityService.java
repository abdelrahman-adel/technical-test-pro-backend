package com.maiia.pro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.mapper.IAvailabilityMapper;
import com.maiia.pro.model.AvailabilityDTO;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.TimeSlotRepository;

@Service
public class ProAvailabilityService implements IProAvailabilityService {

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private IAvailabilityMapper mapper;

	@Override
	public List<AvailabilityDTO> findByPractitionerId(Integer practitionerId) {
		return availabilityRepository.findByPractitionerId(practitionerId).stream().map(mapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<AvailabilityDTO> generateAvailabilities(Integer practitionerId) {
		List<Appointment> appointments = appointmentRepository.findByPractitionerId(practitionerId);
		List<LocalDateTime> appointmentsDates = new ArrayList<>();
		Map<LocalDateTime, Appointment> appointmentsMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(appointments)) {
			appointmentsDates.addAll(appointments.stream().map(Appointment::getStartDate).sorted().collect(Collectors.toList()));
			appointmentsMap.putAll(appointments.stream().collect(Collectors.toMap(Appointment::getStartDate, Function.identity())));
		}

		List<Availability> existingAvailabilities = availabilityRepository.findByPractitionerId(practitionerId);
		Set<LocalDateTime> existingAvailabilitiesDates = new HashSet<>();
		if (!CollectionUtils.isEmpty(existingAvailabilities)) {
			existingAvailabilitiesDates.addAll(existingAvailabilities.stream().map(Availability::getStartDate).collect(Collectors.toSet()));
		}

		List<TimeSlot> timeSlots = timeSlotRepository.findByPractitionerId(practitionerId);
		List<Availability> availabilities = new ArrayList<>();
		if (!CollectionUtils.isEmpty(timeSlots)) {
			timeSlots.stream().forEach(timeSlot -> {
				availabilities.addAll(createAvailabilities(timeSlot, existingAvailabilitiesDates, appointmentsDates, appointmentsMap));
			});
		}

		Iterable<Availability> created = availabilityRepository.saveAll(availabilities);
		return StreamSupport.stream(created.spliterator(), false).map(mapper::mapToDTO).collect(Collectors.toList());
	}

	private List<Availability> createAvailabilities(TimeSlot timeSlot, Set<LocalDateTime> existingAvailabilitiesDates, List<LocalDateTime> appointmentsDates, Map<LocalDateTime, Appointment> appointmentsMap) {

		int appointmentIndex = 0;
		LocalDateTime nextAppointmentDt = null;
		if (appointmentsDates.size() > appointmentIndex) {
			nextAppointmentDt = appointmentsDates.get(appointmentIndex++);
		}

		List<Availability> availabilities = new ArrayList<>();
		LocalDateTime startDate = timeSlot.getStartDate();
		LocalDateTime lastApplicableStartDate = timeSlot.getEndDate().minusMinutes(15);
		while (startDate.isBefore(lastApplicableStartDate) || startDate.isEqual(lastApplicableStartDate)) {
			if (!isValidToAppointment(startDate, nextAppointmentDt)) {
				Appointment nextAppointment = appointmentsMap.get(nextAppointmentDt);
				startDate = nextAppointment.getEndDate();
				if (appointmentsDates.size() > appointmentIndex) {
					nextAppointmentDt = appointmentsDates.get(appointmentIndex++);
				} else {
					nextAppointmentDt = null;
				}
				continue;
			}

			if (isValidToExistingAvailabilities(startDate, existingAvailabilitiesDates)) {
				Availability availability = Availability.builder().practitionerId(timeSlot.getPractitionerId())
						.startDate(startDate).endDate(startDate.plusMinutes(15)).build();
				availabilities.add(availability);
			}

			startDate = startDate.plusMinutes(15);
		}

		return availabilities;
	}

	private boolean isValidToAppointment(LocalDateTime startDate, LocalDateTime nextAppointmentDt) {
		if (nextAppointmentDt == null) {
			return true;
		}

		LocalDateTime applicableStartDate = nextAppointmentDt.minusMinutes(15);
		return startDate.isBefore(applicableStartDate) || startDate.isEqual(applicableStartDate);
	}

	private boolean isValidToExistingAvailabilities(LocalDateTime startDate, Set<LocalDateTime> existingAvailabilitiesDates) {
		return existingAvailabilitiesDates.isEmpty() || !existingAvailabilitiesDates.contains(startDate);
	}
}
