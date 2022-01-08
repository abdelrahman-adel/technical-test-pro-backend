package com.maiia.pro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PractitionerDTO {

	private Integer id;
	private String firstName;
	private String lastName;
	private String speciality;

}
