package com.maiia.pro.service;

import java.util.List;

import com.maiia.pro.entity.TimeSlot;

public interface IProTimeSlotService {

	List<TimeSlot> findByPractitionerId(Integer practitionerId);

}