package com.lxisoft.crimestopper.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.crimestopper.service.dto.CommentDTO;

/**
 * Service Interface for managing Comment.
 */
public interface CommentService {

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save
     * @return the persisted entity
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommentDTO> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * comment in an complaint
     * 
     */
    
	Optional<CommentDTO> saveCommentInComplaint(CommentDTO comment);

	Page<CommentDTO> findAllByComplaintId(Pageable pageable, long complaintId);

	Page<CommentDTO> findAllByUserId(Pageable pageable, long userId);
	
}
