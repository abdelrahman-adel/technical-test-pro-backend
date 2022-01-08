package com.maiia.pro.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

	private Integer id;

	@NotNull(message = "Patient ID is required")
	private Integer patientId;

	@NotNull(message = "Practitioner ID is required")
	private Integer practitionerId;

	@NotNull(message = "StartDate ID is required")
	private LocalDateTime startDate;

	@NotNull(message = "EndDate ID is required")
	private LocalDateTime endDate;

}
