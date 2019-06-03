package com.lxisoft.crimestopper.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.crimestopper.domain.Hashtag;
import com.lxisoft.crimestopper.repository.HashtagRepository;
import com.lxisoft.crimestopper.service.HashtagService;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.service.mapper.HashtagMapper;

/**
 * Service Implementation for managing Hashtag.
 */
@Service
@Transactional
public class HashtagServiceImpl implements HashtagService {

    private final Logger log = LoggerFactory.getLogger(HashtagServiceImpl.class);

    private final HashtagRepository hashtagRepository;

    private final HashtagMapper hashtagMapper;

    public HashtagServiceImpl(HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
        this.hashtagRepository = hashtagRepository;
        this.hashtagMapper = hashtagMapper;
    }

    /**
     * Save a hashtag.
     *
     * @param hashtagDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HashtagDTO save(HashtagDTO hashtagDTO) {
        log.debug("Request to save Hashtag : {}", hashtagDTO);
        Hashtag hashtag = hashtagMapper.toEntity(hashtagDTO);
        hashtag = hashtagRepository.save(hashtag);
        return hashtagMapper.toDto(hashtag);
    }

    /**
     * Get all the hashtags.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HashtagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Hashtags");
        return hashtagRepository.findAll(pageable)
            .map(hashtagMapper::toDto);
    }


    /**
     * Get one hashtag by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HashtagDTO> findOne(Long id) {
        log.debug("Request to get Hashtag : {}", id);
        return hashtagRepository.findById(id)
            .map(hashtagMapper::toDto);
    }

    /**
     * Delete the hashtag by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hashtag : {}", id);    
        hashtagRepository.deleteById(id);
    }
    
    @Override
    public List<HashtagDTO> findTrendingHashtags()
    {
    	  log.debug("Request to get trending  Hashtags : {}"); 
    	  List<Hashtag>list=hashtagRepository.findAll();
    	   Long totalCount=0L;
    	 for(Hashtag ht:list)
    	 {
    		 
    		 totalCount=totalCount+ht.getCount();
    	 }
    	 Long avg=totalCount/list.size();
    	 Pageable pageable=null;
    	 List<HashtagDTO> result=hashtagRepository.findAllHashtagByCountGreaterThan(avg,pageable).map(hashtagMapper::toDto).getContent();
    	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>)())()()()"+result);
    	
    	 if(result.size()>2)
    	 Collections.sort(result);
    	  return result;
    }
    
}
