package com.lxisoft.crimestopper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.Hashtag;
import com.lxisoft.crimestopper.domain.Location;

/**
 * Spring Data repository for the Complaint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	@Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints", countQuery = "select count(distinct complaint) from Complaint complaint")
	Page<Complaint> findAllWithEagerRelationships(Pageable pageable);

	@Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints")
	List<Complaint> findAllWithEagerRelationships();

	@Query("select complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints where complaint.id =:id")
	Optional<Complaint> findOneWithEagerRelationships(@Param("id") Long id);

	/**
	 * to get complaint of an userId
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 */
	Page<Complaint> findByUserId(Long id, Pageable pageable);

	/**
	 * to get complaints by hash tag
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 */
	Page<Complaint> findAllComplaintsByHashtags(Pageable pageable, Hashtag hashtag);

	/**
	 * to get complaints by comment id
	 * 
	 * @param commentId
	 * @return
	 */
	@Query(" SELECT comp FROM "
			+ "Complaint comp INNER JOIN comp.comments comm "
			+ "WHERE comm.id = :id")
	Optional<Complaint> findByCommentId(@Param("id") long commentId);

	/**
	 * to get complaints liked by userId
	 * 
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@Query(" SELECT comp FROM "
			+ "Complaint comp INNER JOIN comp.userResponses ur "
			+ "WHERE ur.userId = :id")
	Page<Complaint> findAllLikedByUserId(@Param("id") long userId, Pageable pageable);
}
