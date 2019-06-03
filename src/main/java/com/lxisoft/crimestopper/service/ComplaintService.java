package com.lxisoft.crimestopper.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.dto.LocationDTO;

/**
 * Service Interface for managing Complaint.
 */
public interface ComplaintService {

	/**
	 * Save a complaint.
	 *
	 * @param complaintDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	ComplaintDTO save(ComplaintDTO complaintDTO);

	/**
	 * Get all the complaints.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<ComplaintDTO> findAll(Pageable pageable);

	/**
	 * Get all the Complaint with eager load of many-to-many relationships.
	 *
	 * @return the list of entities
	 */
	Page<ComplaintDTO> findAllWithEagerRelationships(Pageable pageable);

	/**
	 * Get the "id" complaint.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	Optional<ComplaintDTO> findOne(Long id);

	/**
	 * Delete the "id" complaint.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	/**
	 * Get all the Complaints of friends with eager load of many-to-many
	 * relationships.
	 *
	 * @return the list of entities
	 */

	Page<ComplaintDTO> findAllComplaintsOfFriends(Pageable pageable, Long userId);

	Page<ComplaintDTO> findAllComplaintsOfUserId(Pageable pageable, Long userId);

	/**
	 * Get all the Complaints with given hashTag name,with eager load of
	 * many-to-many relationships.
	 *
	 * @return the list of entities
	 */

	Page<ComplaintDTO> findAllComplaintsByHashtag(Pageable pageable, String searchContent);

	Page<ComplaintDTO> findAllLikedByUserId(Pageable pageable, long userId);

	Optional<ComplaintDTO> findByCommentId(long commentId);
}
