package com.lxisoft.crimestopper.service.impl;

import com.lxisoft.crimestopper.service.UserResponseService;
import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.UserResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UserResponse.
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
}
