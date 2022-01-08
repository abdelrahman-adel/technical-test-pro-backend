package com.maiia.pro.mapper;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.model.AppointmentDTO;

public interface IAppointmentMapper {

	AppointmentDTO mapToDTO(Appointment appointment);

	Appointment mapToEntity(AppointmentDTO appointmentType);

}