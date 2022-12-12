package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Release;
import com.mycompany.myapp.domain.enumeration.FormatType;
import com.mycompany.myapp.domain.enumeration.ReleaseMediaType;
import com.mycompany.myapp.domain.enumeration.ReleaseStatus;
import com.mycompany.myapp.domain.enumeration.ReleaseType;
import com.mycompany.myapp.repository.ReleaseRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ReleaseResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ReleaseResourceIT {

    private static final String DEFAULT_CATALOG_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RELEASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RELEASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_ARTIST = "AAAAAAAAAA";
    private static final String UPDATED_ARTIST = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final ReleaseStatus DEFAULT_STATUS = ReleaseStatus.ACTIVE;
    private static final ReleaseStatus UPDATED_STATUS = ReleaseStatus.ACTIVE;

    private static final ReleaseType DEFAULT_TYPE = ReleaseType.TRACK;
    private static final ReleaseType UPDATED_TYPE = ReleaseType.SINGLE;

    private static final ReleaseMediaType DEFAULT_MEDIA_TYPE = ReleaseMediaType.CD;
    private static final ReleaseMediaType UPDATED_MEDIA_TYPE = ReleaseMediaType.VINYL;

    private static final FormatType DEFAULT_FORMAT = FormatType.DIGITAL;
    private static final FormatType UPDATED_FORMAT = FormatType.PHYSICAL;

    private static final String ENTITY_API_URL = "/api/releases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReleaseRepository releaseRepository;

    @Mock
    private ReleaseRepository releaseRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReleaseMockMvc;

    private Release release;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Release createEntity(EntityManager em) {
        Release release = new Release()
            .catalogNumber(DEFAULT_CATALOG_NUMBER)
            .releaseDate(DEFAULT_RELEASE_DATE)
            .title(DEFAULT_TITLE)
            .version(DEFAULT_VERSION)
            .artist(DEFAULT_ARTIST)
            .label(DEFAULT_LABEL)
            .status(DEFAULT_STATUS)
            .type(DEFAULT_TYPE)
            .mediaType(DEFAULT_MEDIA_TYPE)
            .format(DEFAULT_FORMAT);
        return release;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Release createUpdatedEntity(EntityManager em) {
        Release release = new Release()
            .catalogNumber(UPDATED_CATALOG_NUMBER)
            .releaseDate(UPDATED_RELEASE_DATE)
            .title(UPDATED_TITLE)
            .version(UPDATED_VERSION)
            .artist(UPDATED_ARTIST)
            .label(UPDATED_LABEL)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .mediaType(UPDATED_MEDIA_TYPE)
            .format(UPDATED_FORMAT);
        return release;
    }

    @BeforeEach
    public void initTest() {
        release = createEntity(em);
    }

    @Test
    @Transactional
    void createRelease() throws Exception {
        int databaseSizeBeforeCreate = releaseRepository.findAll().size();
        // Create the Release
        restReleaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(release)))
            .andExpect(status().isCreated());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeCreate + 1);
        Release testRelease = releaseList.get(releaseList.size() - 1);
        assertThat(testRelease.getCatalogNumber()).isEqualTo(DEFAULT_CATALOG_NUMBER);
        assertThat(testRelease.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testRelease.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testRelease.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testRelease.getArtist()).isEqualTo(DEFAULT_ARTIST);
        assertThat(testRelease.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testRelease.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRelease.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testRelease.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testRelease.getFormat()).isEqualTo(DEFAULT_FORMAT);
    }

    @Test
    @Transactional
    void createReleaseWithExistingId() throws Exception {
        // Create the Release with an existing ID
        release.setId(1L);

        int databaseSizeBeforeCreate = releaseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReleaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(release)))
            .andExpect(status().isBadRequest());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReleases() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        // Get all the releaseList
        restReleaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(release.getId().intValue())))
            .andExpect(jsonPath("$.[*].catalogNumber").value(hasItem(DEFAULT_CATALOG_NUMBER)))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].artist").value(hasItem(DEFAULT_ARTIST)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].format").value(hasItem(DEFAULT_FORMAT.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllReleasesWithEagerRelationshipsIsEnabled() throws Exception {
        when(releaseRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restReleaseMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(releaseRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllReleasesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(releaseRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restReleaseMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(releaseRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getRelease() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        // Get the release
        restReleaseMockMvc
            .perform(get(ENTITY_API_URL_ID, release.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(release.getId().intValue()))
            .andExpect(jsonPath("$.catalogNumber").value(DEFAULT_CATALOG_NUMBER))
            .andExpect(jsonPath("$.releaseDate").value(DEFAULT_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.artist").value(DEFAULT_ARTIST))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.mediaType").value(DEFAULT_MEDIA_TYPE.toString()))
            .andExpect(jsonPath("$.format").value(DEFAULT_FORMAT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRelease() throws Exception {
        // Get the release
        restReleaseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRelease() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();

        // Update the release
        Release updatedRelease = releaseRepository.findById(release.getId()).get();
        // Disconnect from session so that the updates on updatedRelease are not directly saved in db
        em.detach(updatedRelease);
        updatedRelease
            .catalogNumber(UPDATED_CATALOG_NUMBER)
            .releaseDate(UPDATED_RELEASE_DATE)
            .title(UPDATED_TITLE)
            .version(UPDATED_VERSION)
            .artist(UPDATED_ARTIST)
            .label(UPDATED_LABEL)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .mediaType(UPDATED_MEDIA_TYPE)
            .format(UPDATED_FORMAT);

        restReleaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelease.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRelease))
            )
            .andExpect(status().isOk());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
        Release testRelease = releaseList.get(releaseList.size() - 1);
        assertThat(testRelease.getCatalogNumber()).isEqualTo(UPDATED_CATALOG_NUMBER);
        assertThat(testRelease.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testRelease.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testRelease.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testRelease.getArtist()).isEqualTo(UPDATED_ARTIST);
        assertThat(testRelease.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testRelease.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRelease.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRelease.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testRelease.getFormat()).isEqualTo(UPDATED_FORMAT);
    }

    @Test
    @Transactional
    void putNonExistingRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, release.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(release))
            )
            .andExpect(status().isBadRequest());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(release))
            )
            .andExpect(status().isBadRequest());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(release)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReleaseWithPatch() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();

        // Update the release using partial update
        Release partialUpdatedRelease = new Release();
        partialUpdatedRelease.setId(release.getId());

        partialUpdatedRelease
            .catalogNumber(UPDATED_CATALOG_NUMBER)
            .title(UPDATED_TITLE)
            .artist(UPDATED_ARTIST)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .format(UPDATED_FORMAT);

        restReleaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelease.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelease))
            )
            .andExpect(status().isOk());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
        Release testRelease = releaseList.get(releaseList.size() - 1);
        assertThat(testRelease.getCatalogNumber()).isEqualTo(UPDATED_CATALOG_NUMBER);
        assertThat(testRelease.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testRelease.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testRelease.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testRelease.getArtist()).isEqualTo(UPDATED_ARTIST);
        assertThat(testRelease.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testRelease.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRelease.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRelease.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testRelease.getFormat()).isEqualTo(UPDATED_FORMAT);
    }

    @Test
    @Transactional
    void fullUpdateReleaseWithPatch() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();

        // Update the release using partial update
        Release partialUpdatedRelease = new Release();
        partialUpdatedRelease.setId(release.getId());

        partialUpdatedRelease
            .catalogNumber(UPDATED_CATALOG_NUMBER)
            .releaseDate(UPDATED_RELEASE_DATE)
            .title(UPDATED_TITLE)
            .version(UPDATED_VERSION)
            .artist(UPDATED_ARTIST)
            .label(UPDATED_LABEL)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE)
            .mediaType(UPDATED_MEDIA_TYPE)
            .format(UPDATED_FORMAT);

        restReleaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelease.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelease))
            )
            .andExpect(status().isOk());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
        Release testRelease = releaseList.get(releaseList.size() - 1);
        assertThat(testRelease.getCatalogNumber()).isEqualTo(UPDATED_CATALOG_NUMBER);
        assertThat(testRelease.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testRelease.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testRelease.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testRelease.getArtist()).isEqualTo(UPDATED_ARTIST);
        assertThat(testRelease.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testRelease.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRelease.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRelease.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testRelease.getFormat()).isEqualTo(UPDATED_FORMAT);
    }

    @Test
    @Transactional
    void patchNonExistingRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, release.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(release))
            )
            .andExpect(status().isBadRequest());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(release))
            )
            .andExpect(status().isBadRequest());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelease() throws Exception {
        int databaseSizeBeforeUpdate = releaseRepository.findAll().size();
        release.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReleaseMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(release)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Release in the database
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelease() throws Exception {
        // Initialize the database
        releaseRepository.saveAndFlush(release);

        int databaseSizeBeforeDelete = releaseRepository.findAll().size();

        // Delete the release
        restReleaseMockMvc
            .perform(delete(ENTITY_API_URL_ID, release.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Release> releaseList = releaseRepository.findAll();
        assertThat(releaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
