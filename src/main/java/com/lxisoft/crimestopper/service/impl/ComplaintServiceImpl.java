package com.lxisoft.crimestopper.service.impl;

import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.repository.ComplaintRepository;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.mapper.ComplaintMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Complaint.
 */
@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final Logger log = LoggerFactory.getLogger(ComplaintServiceImpl.class);

    private final ComplaintRepository complaintRepository;

    private final ComplaintMapper complaintMapper;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper) {
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
    }

    /**
     * Save a complaint.
     *
     * @param complaintDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComplaintDTO save(ComplaintDTO complaintDTO) {
        log.debug("Request to save Complaint : {}", complaintDTO);
        Complaint complaint = complaintMapper.toEntity(complaintDTO);
        complaint = complaintRepository.save(complaint);
        return complaintMapper.toDto(complaint);
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
        return complaintRepository.findAll(pageable)
            .map(complaintMapper::toDto);
    }

    /**
     * Get all the Complaint with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
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
        return complaintRepository.findOneWithEagerRelationships(id)
            .map(complaintMapper::toDto);
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
}
