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

import com.lxisoft.crimestopper.service.HashtagService;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.web.rest.errors.BadRequestAlertException;
import com.lxisoft.crimestopper.web.rest.util.HeaderUtil;
import com.lxisoft.crimestopper.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Hashtag.
 */
@RestController
@RequestMapping("/api")
public class HashtagResource {

    private final Logger log = LoggerFactory.getLogger(HashtagResource.class);

    private static final String ENTITY_NAME = "crimestopperHashtag";

    private final HashtagService hashtagService;

    public HashtagResource(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    /**
     * POST  /hashtags : Create a new hashtag.
     *
     * @param hashtagDTO the hashtagDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hashtagDTO, or with status 400 (Bad Request) if the hashtag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hashtags")
    public ResponseEntity<HashtagDTO> createHashtag(@RequestBody HashtagDTO hashtagDTO) throws URISyntaxException {
        log.debug("REST request to save Hashtag : {}", hashtagDTO);
        if (hashtagDTO.getId() != null) {
            throw new BadRequestAlertException("A new hashtag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HashtagDTO result = hashtagService.save(hashtagDTO);
        return ResponseEntity.created(new URI("/api/hashtags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hashtags : Updates an existing hashtag.
     *
     * @param hashtagDTO the hashtagDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hashtagDTO,
     * or with status 400 (Bad Request) if the hashtagDTO is not valid,
     * or with status 500 (Internal Server Error) if the hashtagDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hashtags")
    public ResponseEntity<HashtagDTO> updateHashtag(@RequestBody HashtagDTO hashtagDTO) throws URISyntaxException {
        log.debug("REST request to update Hashtag : {}", hashtagDTO);
        if (hashtagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HashtagDTO result = hashtagService.save(hashtagDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hashtagDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hashtags : get all the hashtags.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hashtags in body
     */
    @GetMapping("/hashtags")
    public ResponseEntity<List<HashtagDTO>> getAllHashtags(Pageable pageable) {
        log.debug("REST request to get a page of Hashtags");
        Page<HashtagDTO> page = hashtagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hashtags");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /hashtags/:id : get the "id" hashtag.
     *
     * @param id the id of the hashtagDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hashtagDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hashtags/{id}")
    public ResponseEntity<HashtagDTO> getHashtag(@PathVariable Long id) {
        log.debug("REST request to get Hashtag : {}", id);
        Optional<HashtagDTO> hashtagDTO = hashtagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hashtagDTO);
    }

    /**
     * DELETE  /hashtags/:id : delete the "id" hashtag.
     *
     * @param id the id of the hashtagDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hashtags/{id}")
    public ResponseEntity<Void> deleteHashtag(@PathVariable Long id) {
        log.debug("REST request to delete Hashtag : {}", id);
        hashtagService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/trending/hashtags")
    public List<HashtagDTO> trendingHashtags() {
        log.debug("REST request to find all trending  Hashtags : {}");
        List<HashtagDTO>result=hashtagService.findTrendingHashtags();
          return result;
    }
}
