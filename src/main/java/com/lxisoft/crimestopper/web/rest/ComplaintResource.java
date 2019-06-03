package com.lxisoft.crimestopper.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.service.HashtagService;
import com.lxisoft.crimestopper.service.dto.CommentDTO;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.service.dto.LocationDTO;
import com.lxisoft.crimestopper.web.rest.errors.BadRequestAlertException;
import com.lxisoft.crimestopper.web.rest.util.HeaderUtil;
import com.lxisoft.crimestopper.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Complaint.
 */
@RestController
@RequestMapping("/api")
public class ComplaintResource {

	private final Logger log = LoggerFactory.getLogger(ComplaintResource.class);

	private static final String ENTITY_NAME = "crimestopperComplaint";

	private final ComplaintService complaintService;

	@Autowired
	private HashtagService hashtagService;

	public ComplaintResource(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	/**
	 * POST /complaints : Create a new complaint.
	 *
	 * @param complaintDTO
	 *            the complaintDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         complaintDTO, or with status 400 (Bad Request) if the complaint has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/complaints")
	@Timed
	public ResponseEntity<ComplaintDTO> createComplaint(@RequestBody ComplaintDTO complaintDTO)
			throws URISyntaxException {
		log.debug("/>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>REST request to save Complaint : {}",
				complaintDTO);
		if (complaintDTO.getId() != null) {
			throw new BadRequestAlertException("A new complaint cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ComplaintDTO result = complaintService.save(complaintDTO);
		return ResponseEntity.created(new URI("/api/complaints/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /complaints : Updates an existing complaint.
	 *
	 * @param complaintDTO
	 *            the complaintDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         complaintDTO, or with status 400 (Bad Request) if the complaintDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         complaintDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/complaints")
	@Timed
	public ResponseEntity<ComplaintDTO> updateComplaint(@RequestBody ComplaintDTO complaintDTO)
			throws URISyntaxException {
		log.debug("REST request to update Complaint : {}", complaintDTO);
		if (complaintDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		} else if (complaintDTO.getSubject() == null) {
			throw new BadRequestAlertException("invalid subject", ENTITY_NAME, "subject null");
		}
		ComplaintDTO result = complaintService.save(complaintDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, complaintDTO.getId().toString())).body(result);
	}

	/**
	 * GET /complaints : get all the complaints.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is applicable
	 *            for many-to-many)
	 * @return the ResponseEntity with status 200 (OK) and the list of complaints in
	 *         body
	 */
	@GetMapping("/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllComplaints(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
		log.debug("REST request to get a page of Complaints");
		Page<ComplaintDTO> page;
		if (eagerload) {
			page = complaintService.findAllWithEagerRelationships(pageable);
		} else {
			page = complaintService.findAll(pageable);
		}
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /complaints/:id : get the "id" complaint.
	 *
	 * @param id
	 *            the id of the complaintDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         complaintDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/complaints/{id}")
	@Timed
	public ResponseEntity<ComplaintDTO> getComplaint(@PathVariable Long id) {
		log.debug("REST request to get Complaint : {}", id);
		Optional<ComplaintDTO> complaintDTO = complaintService.findOne(id);
		return ResponseUtil.wrapOrNotFound(complaintDTO);
	}

	/**
	 * DELETE /complaints/:id : delete the "id" complaint.
	 *
	 * @param id
	 *            the id of the complaintDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/complaints/{id}")
	@Timed
	public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
		log.debug("REST request to delete Complaint : {}", id);
		complaintService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * GET friends/complaints/:id : get all the complaints of given friends user.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is applicable
	 *            for many-to-many)
	 * @return the ResponseEntity with status 200 (OK) and the list of complaints in
	 *         body
	 */

	@GetMapping("/users/{userId}/friends/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintsOfFriends(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, @PathVariable Long userId) {
		log.debug("REST request to get a page of Complaints");
		Page<ComplaintDTO> page;
		page = complaintService.findAllComplaintsOfFriends(pageable, userId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));

		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@GetMapping("/users/{userId}/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintsOfUserId(@PathVariable long userId, Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
		log.debug("REST request to get  page of Complaints for userid");
		Page<ComplaintDTO> page = complaintService.findAllComplaintsOfUserId(pageable, userId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));
		return ResponseEntity.ok().headers(headers).body(page.getContent());

	}

	@GetMapping("/hashtag/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintsHashtag(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@RequestParam String searchContent) {

		log.debug("REST request to get a page of Complaints");
		Page<ComplaintDTO> page;
		page = complaintService.findAllComplaintsByHashtag(pageable, searchContent);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@GetMapping("/trending/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllTrendingHashtagsAndComplaints(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
		log.debug("REST request to get a page of Complaints");

		List<HashtagDTO> hashtags = hashtagService.findTrendingHashtags();
		List<ComplaintDTO> complaints = new ArrayList<ComplaintDTO>();
		for (HashtagDTO ht : hashtags) {
			complaints.addAll(complaintService.findAllComplaintsByHashtag(pageable, ht.getName()).getContent());
		}
		Page<ComplaintDTO> page = new PageImpl<ComplaintDTO>(complaints, pageable, complaints.size());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));
		return ResponseEntity.ok().headers(headers).body(complaints);
	}

	@GetMapping("/users/{userId}/user-responses/complaints")
	@Timed
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintsLikedByUser(@PathVariable long userId, Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
		log.debug("REST request to get  page of Complaints liked by User: {}", userId);
		Page<ComplaintDTO> page = complaintService.findAllLikedByUserId(pageable, userId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/complaints?eagerload=%b", eagerload));
		return ResponseEntity.ok().headers(headers).body(page.getContent());

	}

	@GetMapping("/comments/{commentId}/complaint")
	@Timed
	public ResponseEntity<ComplaintDTO> getComplaintOfComment(@PathVariable long commentId) {
		log.debug("REST request to get  Location of Complaint: {}", commentId);
		Optional<ComplaintDTO> complaintDTO = complaintService.findByCommentId(commentId);
		return ResponseUtil.wrapOrNotFound(complaintDTO);
	}

}