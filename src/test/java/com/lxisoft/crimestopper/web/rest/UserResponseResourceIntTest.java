package com.lxisoft.crimestopper.web.rest;

import com.lxisoft.crimestopper.CrimestopperApp;

import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.UserResponseService;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.UserResponseMapper;
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

import javax.persistence.EntityManager;
import java.util.List;


import static com.lxisoft.crimestopper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.crimestopper.domain.enumeration.Flag;
/**
 * Test class for the UserResponseResource REST controller.
 *
 * @see UserResponseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrimestopperApp.class)
public class UserResponseResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Flag DEFAULT_FLAG = Flag.LIKE;
    private static final Flag UPDATED_FLAG = Flag.DISLIKE;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserResponseService userResponseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserResponseMockMvc;

    private UserResponse userResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserResponseResource userResponseResource = new UserResponseResource(userResponseService);
        this.restUserResponseMockMvc = MockMvcBuilders.standaloneSetup(userResponseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserResponse createEntity(EntityManager em) {
        UserResponse userResponse = new UserResponse()
            .userId(DEFAULT_USER_ID)
            .flag(DEFAULT_FLAG);
        return userResponse;
    }

    @Before
    public void initTest() {
        userResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserResponse() throws Exception {
        int databaseSizeBeforeCreate = userResponseRepository.findAll().size();

        // Create the UserResponse
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);
        restUserResponseMockMvc.perform(post("/api/user-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isCreated());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeCreate + 1);
        UserResponse testUserResponse = userResponseList.get(userResponseList.size() - 1);
        assertThat(testUserResponse.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserResponse.getFlag()).isEqualTo(DEFAULT_FLAG);
    }

    @Test
    @Transactional
    public void createUserResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userResponseRepository.findAll().size();

        // Create the UserResponse with an existing ID
        userResponse.setId(1L);
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserResponseMockMvc.perform(post("/api/user-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserResponses() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        // Get all the userResponseList
        restUserResponseMockMvc.perform(get("/api/user-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG.toString())));
    }
    
    @Test
    @Transactional
    public void getUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        // Get the userResponse
        restUserResponseMockMvc.perform(get("/api/user-responses/{id}", userResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userResponse.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserResponse() throws Exception {
        // Get the userResponse
        restUserResponseMockMvc.perform(get("/api/user-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        int databaseSizeBeforeUpdate = userResponseRepository.findAll().size();

        // Update the userResponse
        UserResponse updatedUserResponse = userResponseRepository.findById(userResponse.getId()).get();
        // Disconnect from session so that the updates on updatedUserResponse are not directly saved in db
        em.detach(updatedUserResponse);
        updatedUserResponse
            .userId(UPDATED_USER_ID)
            .flag(UPDATED_FLAG);
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(updatedUserResponse);

        restUserResponseMockMvc.perform(put("/api/user-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isOk());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeUpdate);
        UserResponse testUserResponse = userResponseList.get(userResponseList.size() - 1);
        assertThat(testUserResponse.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserResponse.getFlag()).isEqualTo(UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingUserResponse() throws Exception {
        int databaseSizeBeforeUpdate = userResponseRepository.findAll().size();

        // Create the UserResponse
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserResponseMockMvc.perform(put("/api/user-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        int databaseSizeBeforeDelete = userResponseRepository.findAll().size();

        // Get the userResponse
        restUserResponseMockMvc.perform(delete("/api/user-responses/{id}", userResponse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponse.class);
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(1L);
        UserResponse userResponse2 = new UserResponse();
        userResponse2.setId(userResponse1.getId());
        assertThat(userResponse1).isEqualTo(userResponse2);
        userResponse2.setId(2L);
        assertThat(userResponse1).isNotEqualTo(userResponse2);
        userResponse1.setId(null);
        assertThat(userResponse1).isNotEqualTo(userResponse2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponseDTO.class);
        UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setId(1L);
        UserResponseDTO userResponseDTO2 = new UserResponseDTO();
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
        userResponseDTO2.setId(userResponseDTO1.getId());
        assertThat(userResponseDTO1).isEqualTo(userResponseDTO2);
        userResponseDTO2.setId(2L);
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
        userResponseDTO1.setId(null);
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userResponseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userResponseMapper.fromId(null)).isNull();
    }
}
