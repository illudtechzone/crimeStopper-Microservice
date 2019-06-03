package com.lxisoft.crimestopper.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.crimestopper.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.crimestopper.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.Hashtag;
import com.lxisoft.crimestopper.domain.Location;
import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.CommentRepository;
import com.lxisoft.crimestopper.repository.ComplaintRepository;
import com.lxisoft.crimestopper.repository.HashtagRepository;
import com.lxisoft.crimestopper.repository.LocationRepository;
import com.lxisoft.crimestopper.repository.UserRepository;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.service.dto.CommentDTO;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.service.dto.LocationDTO;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.CommentMapper;
import com.lxisoft.crimestopper.service.mapper.ComplaintMapper;
import com.lxisoft.crimestopper.service.mapper.HashtagMapper;
import com.lxisoft.crimestopper.service.mapper.LocationMapper;
import com.lxisoft.crimestopper.service.mapper.UserResponseMapper;

/**by SoOrAjPn ;)
 * Service Implementation for managing Complaint.
 */
@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

	private final Logger log = LoggerFactory.getLogger(ComplaintServiceImpl.class);

	private final ComplaintRepository complaintRepository;

	private final UserRepository userRepository;

	private final ComplaintMapper complaintMapper;

	@Autowired
	HashtagMapper hashtagMapper;

	@Autowired
	LocationMapper locationMapper;

	@Autowired
	private UserResponseMapper userResponseMapper;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Autowired
	private CommentMapper commentMapper;

	private final UserResponseRepository userResponseRepository;

	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;

	@Autowired
	LocationRepository locationRepository;

	public ComplaintServiceImpl(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper,
			UserRepository userRepository, UserResponseRepository userResponseRepository) {

		this.userResponseRepository = userResponseRepository;
		this.complaintRepository = complaintRepository;
		this.complaintMapper = complaintMapper;
		this.userRepository = userRepository;

	}

	/**
	 * Save a complaint.
	 *
	 * @param complaintDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ComplaintDTO save(ComplaintDTO complaintDTO) {
		log.debug("Request to save Complaint start: {}", complaintDTO);

		// set complaint current time the war:time is now in uts not indian
		Instant instant = Instant.now();
		instant = instant.plus(5, ChronoUnit.HOURS).plus(30, ChronoUnit.MINUTES);
		complaintDTO.setTime(instant);
		Set<HashtagDTO> hashtags = new HashSet<HashtagDTO>(identifyHashTags(complaintDTO.getDescription()));
		complaintDTO.setHashtags(hashtags);
		manageLocation(complaintDTO);
		Complaint complaint = complaintMapper.toEntity(complaintDTO);
		complaint = complaintRepository.save(complaint);
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+complaint);
		ComplaintDTO resultDTO = complaintMapper.toDto(complaint);
		setMoreDetails(resultDTO);
		log.debug("Request to save Complaint end result: {}", resultDTO);
		return resultDTO;
	}

	/**
	 * method to find hashtags from an given subject (decription), and saves if an
	 * new hash tag is find
	 * 
	 * @param subject
	 * @return list of HashtagDTO
	 */
	public List<HashtagDTO> identifyHashTags(String subject) {
		log.debug("Entered into finding hashtgas method with subject = " + subject);
		Pattern pattern = Pattern.compile("#{1}\\w+");
		Matcher matcher = pattern.matcher(subject);
		System.out.println("......................." + subject);
		Set<HashtagDTO> hashtags = new HashSet<HashtagDTO>();
		while (matcher.find()) {

			HashtagDTO hashtagDTO = new HashtagDTO();

			Optional<Hashtag> optional = hashtagRepository.findByName(matcher.group(0));
			if (optional.isPresent()) {
				hashtagDTO.setId(optional.get().getId());
				optional.get().setCount(optional.get().getCount() + 1);
				hashtagRepository.save(optional.get());

			} else {
				Hashtag hashtag = new Hashtag();
				hashtag.setCount(1L);
				hashtag.setName(matcher.group(0));
				hashtagDTO = hashtagMapper.toDto(hashtagRepository.save(hashtag));
			}
			hashtags.add(hashtagDTO);
		}
		log.debug("Exiting into finding hashtgas method with hashtags = " + hashtags);
		return new ArrayList<HashtagDTO>(hashtags);
	}

	/**
	 * Get all the complaints.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ComplaintDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Complaints");
		return complaintRepository.findAll(pageable).map(complaintMapper::toDto);
	}

	/**
	 * Get all the Complaint with eager load of many-to-many relationships.
	 *
	 * @return the list of entities
	 */
	@Override
	public Page<ComplaintDTO> findAllWithEagerRelationships(Pageable pageable) {
		return complaintRepository.findAllWithEagerRelationships(pageable).map(complaintMapper::toDto);
	}

	/**
	 * Get one complaint by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ComplaintDTO> findOne(Long id) {
		log.debug("Request to get Complaint : {}", id);
		Optional<Complaint> result = complaintRepository.findOneWithEagerRelationships(id);
		log.debug("##########################<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>#" + result.get().getHashtags());
		;
		Optional<ComplaintDTO> resultDTO = result.map(complaintMapper::toDto);
		long likes = userResponseRepository.countLikesOfComplaint(result.get());
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOflike=" + likes);

		long dislikes = userResponseRepository.countDislikesOfComplaint(result.get());
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOfdislike=" + dislikes);

		resultDTO.get().setNoOfLikes(likes);
		resultDTO.get().setNoOfDislikes(dislikes);
		return resultDTO;
	}

	/**
	 * Delete the complaint by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Complaint : {}", id);
		complaintRepository.deleteById(id);
	}

	/**
	 * Get one complaints of friends by userId.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */

	@Override
	public Page<ComplaintDTO> findAllComplaintsOfFriends(Pageable pageable, Long userId) {

		log.debug("request to get all complaints of friends by userId:" + userId);
		List<ComplaintDTO> list = new ArrayList<ComplaintDTO>();
		List<UserRegistrationDTO> users = userRegistrationResourceApi.getAllFriendsUsingGET(userId).getBody();
		UserRegistrationDTO currentUser = userRegistrationResourceApi.getUserRegistrationUsingGET(userId).getBody();
		users.add(currentUser);
		complaintRepository.findByUserId(userId, pageable);
		for (UserRegistrationDTO userDTO : users) {
			List<ComplaintDTO> complaints = complaintRepository.findByUserId(userDTO.getId(), pageable)
					.map(complaintMapper::toDto).getContent();
			for (ComplaintDTO complaint : complaints) {

				setMoreDetails(complaint);
			}
			list.addAll(complaints);

		}

		Page<ComplaintDTO> pages = new PageImpl<ComplaintDTO>(list, pageable, list.size());
		return pages;
	}

	/**
	 * the method is used to find complaints by hashtag
	 * 
	 * @param pageable
	 * @param searchContent
	 * @return list of complaints
	 */

	@Override
	public Page<ComplaintDTO> findAllComplaintsByHashtag(Pageable pageable, String searchContent) {

		log.debug("request to get all complaints by search content:" + searchContent);
		List<ComplaintDTO> list = new ArrayList<ComplaintDTO>();
		Optional<Hashtag> hashtag = hashtagRepository.findByName(searchContent);
		List<ComplaintDTO> complaints = new ArrayList<ComplaintDTO>();
		if (hashtag.isPresent()) {
			Page<Complaint> pageComplaint = complaintRepository.findAllComplaintsByHashtags(pageable, hashtag.get());
			if (pageComplaint.hasContent())
				complaints = pageComplaint.map(complaintMapper::toDto).getContent();

			for (ComplaintDTO complaint : complaints) {

				setMoreDetails(complaint);

			}
			list.addAll(complaints);

		}

		Page<ComplaintDTO> pages = new PageImpl<ComplaintDTO>(list, pageable, list.size());
		return pages;

	}

	/**
	 * to get all complaints by an given user id
	 * 
	 * @param pageable
	 * @param userId
	 * @return List<complaintDTO>
	 */
	@Override
	public Page<ComplaintDTO> findAllComplaintsOfUserId(Pageable pageable, Long userId) {

		Page<ComplaintDTO> complaints = complaintRepository.findByUserId(userId, pageable).map(complaintMapper::toDto);

		return complaints;

	}

	/**
	 * the method is used to encode an image and set to complaint and set the user
	 * response of requested user if available and set posted user name to complaint
	 * 
	 * @param complaint
	 * @return complaintDTO
	 */
	public ComplaintDTO setMoreDetails(ComplaintDTO complaint) {
		if (complaint.getMedia() != null) {
			String image = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(complaint.getMedia());

			complaint.setImage(image);
		}
		UserRegistrationDTO userDTO = userRegistrationResourceApi.getUserRegistrationUsingGET(complaint.getUserId())
				.getBody();
		complaint.setUserName(userDTO.getFirstName() + " " + userDTO.getLastName());

		Optional<UserResponse> optional = userResponseRepository
				.findUserResponseByUserIdAndComplaintId(complaint.getUserId(), complaint.getId());
		Optional<UserResponseDTO> optionalDTO = optional.map(userResponseMapper::toDto);
		if (optionalDTO.isPresent()) {
			complaint.setUserResponse(optionalDTO.get());

		} else {
			complaint.setUserResponse(new UserResponseDTO());
		}
		return complaint;
	}

	/**
	 * the method is used to save an location if it alredy has a one then it get the
	 * location id and set it to the complaint
	 * 
	 * @param complaintDTO
	 * @return comlpaintDTO
	 */

	public ComplaintDTO manageLocation(ComplaintDTO complaintDTO) {
		// CAUTION location stuffs start
		log.debug("location:::::::::::::::::::" + complaintDTO.getLocation());
		Optional<Location> optionalLocation=Optional.of(new Location());
		if(complaintDTO.getLocation()!=null)
		 optionalLocation = locationRepository.findBylatitudeAndLongitude(
				complaintDTO.getLocation().getLatitude(), complaintDTO.getLocation().getLongitude());
		if (optionalLocation.isPresent()) {
			complaintDTO.setLocationId(optionalLocation.get().getId());//if is used check if an location alredy saved then it get the id and set it to complaint
		} else {
			Optional<Location> location=Optional.of(complaintDTO.getLocation()).map(locationMapper::toEntity);
			Location savedLocation = locationRepository.save(location.get());
			complaintDTO.setLocationId(savedLocation.getId());//else save the location and the location set to the complaint
	
		}

		return complaintDTO;

	}
	

	@Override
	@Transactional(readOnly = true)
	public Page<ComplaintDTO> findAllLikedByUserId(Pageable pageable, long userId) {
		return complaintRepository.findAllLikedByUserId(userId, pageable).map(complaintMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ComplaintDTO> findByCommentId(long commentId) {
		return complaintRepository.findByCommentId(commentId).map(complaintMapper::toDto);
	}
}