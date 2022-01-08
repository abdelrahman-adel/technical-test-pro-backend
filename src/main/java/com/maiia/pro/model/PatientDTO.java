package com.maiia.pro.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

	private Integer id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;

}
