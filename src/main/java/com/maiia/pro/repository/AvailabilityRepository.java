package com.maiia.pro.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maiia.pro.entity.Availability;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {

	List<Availability> findByPractitionerId(Integer practitionerId);

	Optional<Availability> findByPractitionerIdAndStartDateAndEndDate(Integer practitionerId, LocalDateTime startDate, LocalDateTime endDate);
}
