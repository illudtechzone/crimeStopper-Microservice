package com.lxisoft.crimestopper.service;

import com.lxisoft.crimestopper.service.dto.HashtagDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Hashtag.
 */
public interface HashtagService {

    /**
     * Save a hashtag.
     *
     * @param hashtagDTO the entity to save
     * @return the persisted entity
     */
    HashtagDTO save(HashtagDTO hashtagDTO);

    /**
     * Get all the hashtags.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HashtagDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hashtag.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HashtagDTO> findOne(Long id);

    /**
     * Delete the "id" hashtag.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
