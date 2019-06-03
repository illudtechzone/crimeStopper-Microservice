package com.lxisoft.crimestopper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Comment;
import com.lxisoft.crimestopper.service.dto.CommentDTO;

/**
 * Spring Data repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public Page<Comment> findAllByComplaintId(Pageable pageable, Long complaintId);

	public Page<Comment> findAllByUserId(Pageable pageable, long userId);

}
