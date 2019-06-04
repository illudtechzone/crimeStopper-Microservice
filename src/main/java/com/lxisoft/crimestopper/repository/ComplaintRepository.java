package com.lxisoft.crimestopper.repository;

import com.lxisoft.crimestopper.domain.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Complaint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints left join fetch complaint.hashtags",
        countQuery = "select count(distinct complaint) from Complaint complaint")
    Page<Complaint> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints left join fetch complaint.hashtags")
    List<Complaint> findAllWithEagerRelationships();

    @Query("select complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints left join fetch complaint.hashtags where complaint.id =:id")
    Optional<Complaint> findOneWithEagerRelationships(@Param("id") Long id);

}
