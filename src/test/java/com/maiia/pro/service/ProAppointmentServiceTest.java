package com.maiia.pro.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maiia.pro.EntityFactory;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.model.AppointmentDTO;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.PractitionerRepository;
import com.maiia.pro.repository.TimeSlotRepository;

@SpringBootTest
class ProAppointmentServiceTest {

	private final static Integer PATIENT_ID = 657679;

	private final EntityFactory entityFactory = new EntityFactory();

	@Autowired
	private IProAppointmentService appointmentService;

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private PractitionerRepository practitionerRepository;

	@Test
	void testCreate_Success() {
		Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
		LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
		LocalDateTime endDate = startDate.plusMinutes(15);
		timeSlotRepository.save(entityFactory.createTimeSlot(practitioner.getId(), startDate, endDate));
		availabilityRepository.save(entityFactory.createAvailability(practitioner.getId(), startDate, endDate));

		AppointmentDTO appointment = AppointmentDTO.builder()
			.patientId(PATIENT_ID)
			.practitionerId(practitioner.getId())
			.startDate(startDate)
			.endDate(endDate)
			.build();
		appointmentService.create(appointment);
	}

	@Test
	void testCreate_UnavailableTime() {
		Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
		LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
		LocalDateTime endDate = startDate.plusMinutes(15);
		timeSlotRepository.save(entityFactory.createTimeSlot(practitioner.getId(), startDate, endDate));
		availabilityRepository.save(entityFactory.createAvailability(practitioner.getId(), startDate, endDate));

		AppointmentDTO appointment = AppointmentDTO.builder()
			.patientId(PATIENT_ID)
			.practitionerId(practitioner.getId())
			.startDate(startDate.plusHours(1))
			.endDate(endDate.plusHours(1))
			.build();
		assertThrows(IllegalStateException.class, () -> appointmentService.create(appointment));
	}

}
