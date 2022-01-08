package com.maiia.pro.mapper;

import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.model.PractitionerDTO;

public interface IPractitionerMapper {

	PractitionerDTO mapToDTO(Practitioner practitioner);

}