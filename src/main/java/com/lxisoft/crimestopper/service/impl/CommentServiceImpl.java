package com.lxisoft.crimestopper.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.crimestopper.domain.Comment;
import com.lxisoft.crimestopper.repository.CommentRepository;
import com.lxisoft.crimestopper.service.CommentService;
import com.lxisoft.crimestopper.service.dto.CommentDTO;
import com.lxisoft.crimestopper.service.mapper.CommentMapper;

/**
 * Service Implementation for managing Comment.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	@Autowired
	private final CommentRepository commentRepository;
	@Autowired
	private final CommentMapper commentMapper;

	public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
	}

	/**
	 * Save a comment.
	 *
	 * @param commentDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public CommentDTO save(CommentDTO commentDTO) {
		log.debug("Request to save Comment : {}", commentDTO);

		Comment comment = commentMapper.toEntity(commentDTO);
		comment = commentRepository.save(comment);
		return commentMapper.toDto(comment);
	}

	/**
	 * Get all the comments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<CommentDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Comments");
		return commentRepository.findAll(pageable).map(commentMapper::toDto);
	}

	/**
	 * Get one comment by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<CommentDTO> findOne(Long id) {
		log.debug("Request to get Comment : {}", id);
		return commentRepository.findById(id).map(commentMapper::toDto);
	}

	/**
	 * Delete the comment by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Comment : {}", id);
		commentRepository.deleteById(id);
	}

	/**
	 * save an comment of an complaint
	 * 
	 * @param commentDTO
	 * @Return commentDTO
	 */
	@Override
	public Optional<CommentDTO> saveCommentInComplaint(CommentDTO commentDTO) {
		log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<comment in a complaint <<<<<<<<<<<<< " + commentDTO);
		Comment comment = commentMapper.toEntity(commentDTO);
		comment = commentRepository.save(comment);
		return Optional.of(commentMapper.toDto(comment));

	}

	@Override
	public Page<CommentDTO> findAllByComplaintId(Pageable pageable, long complaintId) {
		return commentRepository.findAllByComplaintId(pageable, complaintId).map(commentMapper::toDto);
	}

	@Override
	public Page<CommentDTO> findAllByUserId(Pageable pageable, long userId) {
		return commentRepository.findAllByUserId(pageable, userId).map(commentMapper::toDto);
	}
}
