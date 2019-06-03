package com.lxisoft.crimestopper.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.crimestopper.service.UserResponseService;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.web.rest.errors.BadRequestAlertException;
import com.lxisoft.crimestopper.web.rest.util.HeaderUtil;
import com.lxisoft.crimestopper.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserResponse.
 */
@RestController
@RequestMapping("/api")
public class UserResponseResource {

	private final Logger log = LoggerFactory.getLogger(UserResponseResource.class);

	private static final String ENTITY_NAME = "crimestopperUserResponse";

	private final UserResponseService userResponseService;

	public UserResponseResource(UserResponseService userResponseService) {
		this.userResponseService = userResponseService;
	}

	/**
	 * POST /user-responses : Create a new userResponse.
	 *
	 * @param userResponseDTO
	 *            the userResponseDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         userResponseDTO, or with status 400 (Bad Request) if the userResponse
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/user-responses")
	@Timed
	public ResponseEntity<UserResponseDTO> createUserResponse(@RequestBody UserResponseDTO userResponseDTO)
			throws URISyntaxException {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>REST request to save UserResponse : {}", userResponseDTO);
		if (userResponseDTO.getId() != null) {
			throw new BadRequestAlertException("A new userResponse cannot already have an ID", ENTITY_NAME, "idexists");
		}
		UserResponseDTO result = userResponseService.save(userResponseDTO);
		return ResponseEntity.created(new URI("/api/user-responses/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /user-responses : Updates an existing userResponse.
	 *
	 * @param userResponseDTO
	 *            the userResponseDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         userResponseDTO, or with status 400 (Bad Request) if the
	 *         userResponseDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the userResponseDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/user-responses")
	@Timed
	public ResponseEntity<UserResponseDTO> updateUserResponse(@RequestBody UserResponseDTO userResponseDTO)
			throws URISyntaxException {
		log.debug("REST request to update UserResponse : {}", userResponseDTO);
		if (userResponseDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		UserResponseDTO result = userResponseService.save(userResponseDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userResponseDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /user-responses : get all the userResponses.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of userResponses
	 *         in body
	 */
	@GetMapping("/user-responses")
	@Timed
	public ResponseEntity<List<UserResponseDTO>> getAllUserResponses(Pageable pageable) {
		log.debug("REST request to get a page of UserResponses");
		Page<UserResponseDTO> page = userResponseService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-responses");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /user-responses/:id : get the "id" userResponse.
	 *
	 * @param id
	 *            the id of the userResponseDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         userResponseDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/user-responses/{id}")
	@Timed
	public ResponseEntity<UserResponseDTO> getUserResponse(@PathVariable Long id) {
		log.debug("REST request to get UserResponse : {}", id);
		Optional<UserResponseDTO> userResponseDTO = userResponseService.findOne(id);
		return ResponseUtil.wrapOrNotFound(userResponseDTO);
	}

	/**
	 * DELETE /user-responses/:id : delete the "id" userResponse.
	 *
	 * @param id
	 *            the id of the userResponseDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/user-responses/{id}")
	@Timed
	public ResponseEntity<Void> deleteUserResponse(@PathVariable Long id) {
		log.debug("REST request to delete UserResponse : {}", id);
		userResponseService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * POST /compliant/like : like the "id" complaint
	 * 
	 * @param id
	 *            the id of the userResponseDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@PostMapping("complaint/user-response")
	@Timed

	public ResponseEntity<UserResponseDTO> addUserResponseOfComplaint(@RequestBody UserResponseDTO userResponse)
			throws URISyntaxException {

		log.debug("give userResponce to a complaint userID+:" + userResponse.getUserId() + "  complaintId:"
				+ userResponse.getId());

		if (userResponse.getComplaintId() == null) {
			throw new BadRequestAlertException("invalid complaintId", ENTITY_NAME, "COMPLAINT ID NOT FOUND");
		}
		if (userResponse.getUserId() == null) {
			throw new BadRequestAlertException("invalid user id", ENTITY_NAME, "user ID not found");
		}

		if (userResponse.getFlag() == null) {
			throw new BadRequestAlertException("invalid user id", ENTITY_NAME, "flag not found");
		}

		return ResponseUtil.wrapOrNotFound(userResponseService.saveComplaintUserResponse(userResponse));

	}

	/**
	* 
	* 
	*/
	@PostMapping("comment/user-response")
	@Timed

	public ResponseEntity<UserResponseDTO> addUserResponseOfComment(@RequestBody UserResponseDTO userResponse) {

		log.debug("post resquest to save an user responce of an comment:" + userResponse);

		userResponseService.saveCommentUserResponse(userResponse);

		// serResponseService.saveCommentUserResponce();

		return null;

	}

	/**
	 * to get userResponce of An complaint
	 * 
	 * @param complaintId
	 * @param pageable
	 * @return
	 */

	@GetMapping("complaints/{complaintId}/user-responses")
	@Timed
	public ResponseEntity<List<UserResponseDTO>> getUserResponseOfComplaint(@PathVariable Long complaintId,
			Pageable pageable) {
		log.debug("request to get an userResponse of given complaint" + complaintId);

		Page<UserResponseDTO> result = userResponseService.getComplaintUserResponses(complaintId, pageable);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/complaint/user-responses");
		return ResponseEntity.ok().headers(headers).body(result.getContent());

	}

	/**
	 * to get userResponce of An complaint
	 * 
	 * @param complaintId
	 * @param pageable
	 * @return
	 */

	@GetMapping("comments/{commentId}/user-responses")
	@Timed
	public ResponseEntity<List<UserResponseDTO>> getUserResponseOfComment(@PathVariable Long commentId,
			Pageable pageable) {
		log.debug("request to get an userResponse of given comment" + commentId);

		Page<UserResponseDTO> result = userResponseService.getCommentUserResponses(commentId, pageable);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/comment/user-responses");
		return ResponseEntity.ok().headers(headers).body(result.getContent());

	}

	/**
	 * to get userResponce of An complaint
	 * 
	 * @param complaintId
	 * @param pageable
	 * @return
	 */

	@GetMapping("replies/{replyId}/user-responses")
	@Timed
	public ResponseEntity<List<UserResponseDTO>> getUserResponseOfReply(@PathVariable Long replyId, Pageable pageable) {
		log.debug("request to get a userResponse of reply id:" + replyId);

		Page<UserResponseDTO> result = userResponseService.getReplyUserResponses(replyId, pageable);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/reply/user-responses");
		return ResponseEntity.ok().headers(headers).body(result.getContent());

	}

	@GetMapping("users/{userId}/complaints/{complaintId}/user_response")
	@Timed
	public void test2(@PathVariable Long userId, @PathVariable Long complaintId) {

		userResponseService.findByUserIdAndComplaintId(userId, complaintId);

	}

}
