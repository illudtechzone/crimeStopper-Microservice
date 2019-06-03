package com.lxisoft.crimestopper.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Location;
import com.lxisoft.crimestopper.service.dto.LocationDTO;

/**
 * Spring Data repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	// Optional<Location> findBylatitudeAndlongitutde(Double latitude, Double
	// longitutde);

	/*
	 * @Query(" select location.* from complaint " +
	 * "inner join location on complaint.location_id=location.id " +
	 * "where complaint.id=1;")
	 */
	@Query(" select l from Complaint c inner join c.location l where c.id =:id")
	Optional<Location> findByComplaintId(@Param("id") long complaintId);

	Optional<Location> findBylatitudeAndLongitude(Double latitude, Double longitude);

}
