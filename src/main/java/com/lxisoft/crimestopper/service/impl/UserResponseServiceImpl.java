package com.lxisoft.crimestopper.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.UserResponseService;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.UserResponseMapper;

/**
 * Service Implementation for managing UserResponse.
 */

 
/**
 * @author sooraj pn
 *
 */
@Service
@Transactional
public class UserResponseServiceImpl implements UserResponseService {

    private final Logger log = LoggerFactory.getLogger(UserResponseServiceImpl.class);

    private final UserResponseRepository userResponseRepository;

    private final UserResponseMapper userResponseMapper;

    public UserResponseServiceImpl(UserResponseRepository userResponseRepository, UserResponseMapper userResponseMapper) {
        this.userResponseRepository = userResponseRepository;
        this.userResponseMapper = userResponseMapper;
    }

    /**
     * Save a userResponse.
     *
     * @param userResponseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserResponseDTO save(UserResponseDTO userResponseDTO) {
        log.debug("Request to save UserResponse : {}", userResponseDTO);

        UserResponse userResponse = userResponseMapper.toEntity(userResponseDTO);
        userResponse = userResponseRepository.save(userResponse);
        return userResponseMapper.toDto(userResponse);
    }

    /**
     * Get all the userResponses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserResponses");
        return userResponseRepository.findAll(pageable)
            .map(userResponseMapper::toDto);
    }


    /**
     * Get one userResponse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> findOne(Long id) {
        log.debug("Request to get UserResponse : {}", id);
        return userResponseRepository.findById(id)
            .map(userResponseMapper::toDto);
    }

    /**
     * Delete the userResponse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserResponse : {}", id);
        userResponseRepository.deleteById(id);
    }
    
    
    /**
     * method to like dislike an complaint
     * @param complaintId
     * @param userID
     * @return
     * 
     */

	@Override
	public Optional<UserResponseDTO> saveComplaintUserResponse(UserResponseDTO userResponse) {
		
		log.debug("request to save or update an userResport "+userResponse);
	//	Optional<UserResponseDTO>result=userResponseRepository.findUserResponseWithUserIdAndComplaintId(userResponsecomplaintId,userId).map(userResponseMapper::toDto);
		UserResponseDTO urDTO=userResponseMapper.toDto(userResponseRepository.save(userResponseMapper.toEntity(userResponse)));
		Optional<UserResponseDTO>optional=Optional.of(urDTO);
		return optional;
		
	}
	
	/**
	 * method to save ann user responce towards an comment
	 * @param userResponce 
	 * @return userResponce
	 */

	@Override
	public Optional<UserResponseDTO> saveCommentUserResponse(UserResponseDTO userResponse) {
		
		log.debug("request to save an user responce for an comlaint:"+userResponse);
		
		
		return null;
		
	}
	
	/**
	 * method to get user responses of an given complaint id
	 * 
	 */

	@Override
	public Page<UserResponseDTO> getComplaintUserResponses(Long complaintId, Pageable pageable) {
		log.debug("request to get user responses of an given complaint "+complaintId);
		Page<UserResponseDTO>result=userResponseRepository.findAllUserResponseByComplaintId(complaintId,pageable).map(userResponseMapper::toDto);
		return result;
	}

	/**
	 * method to get user Responses of an given comment id
	 * 
	 */
	
	@Override
	public Page<UserResponseDTO> getCommentUserResponses(Long commentId, Pageable pageable) {
	
		log.debug("request to get user responses of an given commentid:"+commentId);
		Page<UserResponseDTO>result=userResponseRepository.findAllUserResponseByCommentId(commentId,pageable).map(userResponseMapper::toDto);
		return result;
		
	
	}
	
	/**
	 * method to get all user response of an reply
	 * 
	 */
	
	@Override
	public Page<UserResponseDTO> getReplyUserResponses(Long replyId, Pageable pageable) {
		
		log.debug("request to get user responses of an given reply id: "+replyId);
		Page<UserResponseDTO>result=userResponseRepository.findAllUserResponseByReplyId(replyId,pageable).map(userResponseMapper::toDto);
		return result;
		
	}

	@Override
	public Page<UserResponseDTO> findByUserIdAndComplaintId(Long userId, Long complaintId) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>"+userResponseRepository.findUserResponseByUserIdAndComplaintId(userId,complaintId));
		return null;
	}
	
	
} 
