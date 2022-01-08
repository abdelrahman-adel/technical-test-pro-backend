package com.maiia.pro.mapper;

import org.springframework.stereotype.Service;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.model.AppointmentDTO;

@Service
public class AppointmentMapper implements IAppointmentMapper {

	@Override
	public AppointmentDTO mapToDTO(Appointment appointment) {
		AppointmentDTO appointmentType = new AppointmentDTO();
		appointmentType.setId(appointment.getId());
		appointmentType.setPatientId(appointment.getPatientId());
		appointmentType.setPractitionerId(appointment.getPractitionerId());
		appointmentType.setStartDate(appointment.getStartDate());
		appointmentType.setEndDate(appointment.getEndDate());
		return appointmentType;
	}

	@Override
	public Appointment mapToEntity(AppointmentDTO appointmentType) {
		Appointment appointment = new Appointment();
		appointment.setId(appointmentType.getId());
		appointment.setPatientId(appointmentType.getPatientId());
		appointment.setPractitionerId(appointmentType.getPractitionerId());
		appointment.setStartDate(appointmentType.getStartDate());
		appointment.setEndDate(appointmentType.getEndDate());
		return appointment;
	}

}
