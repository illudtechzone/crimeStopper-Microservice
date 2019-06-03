package com.lxisoft.crimestopper.web.rest;

import com.lxisoft.crimestopper.CrimestopperApp;

import com.lxisoft.crimestopper.domain.Hashtag;
import com.lxisoft.crimestopper.repository.HashtagRepository;
import com.lxisoft.crimestopper.service.HashtagService;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.service.mapper.HashtagMapper;
import com.lxisoft.crimestopper.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.lxisoft.crimestopper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HashtagResource REST controller.
 *
 * @see HashtagResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrimestopperApp.class)
public class HashtagResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COUNT = 1L;
    private static final Long UPDATED_COUNT = 2L;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private HashtagMapper hashtagMapper;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restHashtagMockMvc;

    private Hashtag hashtag;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HashtagResource hashtagResource = new HashtagResource(hashtagService);
        this.restHashtagMockMvc = MockMvcBuilders.standaloneSetup(hashtagResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hashtag createEntity(EntityManager em) {
        Hashtag hashtag = new Hashtag()
            .name(DEFAULT_NAME)
            .count(DEFAULT_COUNT);
        return hashtag;
    }

    @Before
    public void initTest() {
        hashtag = createEntity(em);
    }

    @Test
    @Transactional
    public void createHashtag() throws Exception {
        int databaseSizeBeforeCreate = hashtagRepository.findAll().size();

        // Create the Hashtag
        HashtagDTO hashtagDTO = hashtagMapper.toDto(hashtag);
        restHashtagMockMvc.perform(post("/api/hashtags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hashtagDTO)))
            .andExpect(status().isCreated());

        // Validate the Hashtag in the database
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        assertThat(hashtagList).hasSize(databaseSizeBeforeCreate + 1);
        Hashtag testHashtag = hashtagList.get(hashtagList.size() - 1);
        assertThat(testHashtag.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHashtag.getCount()).isEqualTo(DEFAULT_COUNT);
    }

    @Test
    @Transactional
    public void createHashtagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hashtagRepository.findAll().size();

        // Create the Hashtag with an existing ID
        hashtag.setId(1L);
        HashtagDTO hashtagDTO = hashtagMapper.toDto(hashtag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHashtagMockMvc.perform(post("/api/hashtags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hashtagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hashtag in the database
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        assertThat(hashtagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHashtags() throws Exception {
        // Initialize the database
        hashtagRepository.saveAndFlush(hashtag);

        // Get all the hashtagList
        restHashtagMockMvc.perform(get("/api/hashtags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hashtag.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT.intValue())));
    }
    
    @Test
    @Transactional
    public void getHashtag() throws Exception {
        // Initialize the database
        hashtagRepository.saveAndFlush(hashtag);

        // Get the hashtag
        restHashtagMockMvc.perform(get("/api/hashtags/{id}", hashtag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hashtag.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHashtag() throws Exception {
        // Get the hashtag
        restHashtagMockMvc.perform(get("/api/hashtags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHashtag() throws Exception {
        // Initialize the database
        hashtagRepository.saveAndFlush(hashtag);

        int databaseSizeBeforeUpdate = hashtagRepository.findAll().size();

        // Update the hashtag
        Hashtag updatedHashtag = hashtagRepository.findById(hashtag.getId()).get();
        // Disconnect from session so that the updates on updatedHashtag are not directly saved in db
        em.detach(updatedHashtag);
        updatedHashtag
            .name(UPDATED_NAME)
            .count(UPDATED_COUNT);
        HashtagDTO hashtagDTO = hashtagMapper.toDto(updatedHashtag);

        restHashtagMockMvc.perform(put("/api/hashtags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hashtagDTO)))
            .andExpect(status().isOk());

        // Validate the Hashtag in the database
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        assertThat(hashtagList).hasSize(databaseSizeBeforeUpdate);
        Hashtag testHashtag = hashtagList.get(hashtagList.size() - 1);
        assertThat(testHashtag.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHashtag.getCount()).isEqualTo(UPDATED_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingHashtag() throws Exception {
        int databaseSizeBeforeUpdate = hashtagRepository.findAll().size();

        // Create the Hashtag
        HashtagDTO hashtagDTO = hashtagMapper.toDto(hashtag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHashtagMockMvc.perform(put("/api/hashtags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hashtagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hashtag in the database
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        assertThat(hashtagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHashtag() throws Exception {
        // Initialize the database
        hashtagRepository.saveAndFlush(hashtag);

        int databaseSizeBeforeDelete = hashtagRepository.findAll().size();

        // Delete the hashtag
        restHashtagMockMvc.perform(delete("/api/hashtags/{id}", hashtag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        assertThat(hashtagList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hashtag.class);
        Hashtag hashtag1 = new Hashtag();
        hashtag1.setId(1L);
        Hashtag hashtag2 = new Hashtag();
        hashtag2.setId(hashtag1.getId());
        assertThat(hashtag1).isEqualTo(hashtag2);
        hashtag2.setId(2L);
        assertThat(hashtag1).isNotEqualTo(hashtag2);
        hashtag1.setId(null);
        assertThat(hashtag1).isNotEqualTo(hashtag2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HashtagDTO.class);
        HashtagDTO hashtagDTO1 = new HashtagDTO();
        hashtagDTO1.setId(1L);
        HashtagDTO hashtagDTO2 = new HashtagDTO();
        assertThat(hashtagDTO1).isNotEqualTo(hashtagDTO2);
        hashtagDTO2.setId(hashtagDTO1.getId());
        assertThat(hashtagDTO1).isEqualTo(hashtagDTO2);
        hashtagDTO2.setId(2L);
        assertThat(hashtagDTO1).isNotEqualTo(hashtagDTO2);
        hashtagDTO1.setId(null);
        assertThat(hashtagDTO1).isNotEqualTo(hashtagDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hashtagMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hashtagMapper.fromId(null)).isNull();
    }
}
