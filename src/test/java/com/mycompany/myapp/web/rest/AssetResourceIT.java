package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Asset;
import com.mycompany.myapp.domain.enumeration.AssetType;
import com.mycompany.myapp.repository.AssetRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AssetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AssetResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ARTIST = "AAAAAAAAAA";
    private static final String UPDATED_ARTIST = "BBBBBBBBBB";

    private static final AssetType DEFAULT_TYPE = AssetType.RECORDING;
    private static final AssetType UPDATED_TYPE = AssetType.MUSICALWORKS;

    private static final String DEFAULT_ISRC = "AAAAAAAAAA";
    private static final String UPDATED_ISRC = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_DURATION = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_CYEAR_CLINE = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_CYEAR_CLINE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_PYEAR_PLINE = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_PYEAR_PLINE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_MAIN_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_MAIN_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_MAIN_GENRE_SUB = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_MAIN_GENRE_SUB = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_ALTERNATIVE_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_ALTERNATIVE_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_ALTERNATIVE_GENRE_SUB = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_MASTER_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_MASTER_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_YEAR_RECORDING = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_YEAR_RECORDING = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_LABEL_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_LABEL_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_BPM = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_BPM = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_PUBLISHERS = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_PUBLISHERS = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_LABEL_COPY = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_LABEL_COPY = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_ADDITIONAL_LABEL_COPY = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_ADDITIONAL_LABEL_COPY = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_TAG_WORDS = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_TAG_WORDS = "BBBBBBBBBB";

    private static final String DEFAULT_RECORDING_SUGGESTED_USES = "AAAAAAAAAA";
    private static final String UPDATED_RECORDING_SUGGESTED_USES = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_TYPE_OF_WORK = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_TYPE_OF_WORK = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_VERSION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_VERSION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_DURATION = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_LANGUAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MUSICALWORKS_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MUSICALWORKS_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MUSICALWORKS_PUBLICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MUSICALWORKS_PUBLICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MUSICALWORKS_REGISTRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MUSICALWORKS_REGISTRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_MUSICALWORKS_UNCLEARED_SAMPLE = false;
    private static final Boolean UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE = true;

    private static final Boolean DEFAULT_MUSICALWORKS_CONTROLLED = false;
    private static final Boolean UPDATED_MUSICALWORKS_CONTROLLED = true;

    private static final String DEFAULT_MUSICALWORKS_RELATED_ISRCS = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_RELATED_ISRCS = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_TERRITORY = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_TERRITORY = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_CRC_PUBLISHER = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_CRC_PUBLISHER = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_ADMINISTRATOR = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_ADMINISTRATOR = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_SUB_PUBLISHER = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_SUB_PUBLISHER = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_INCOME_COLLECTOR = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_INCOME_COLLECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICALWORKS_TERRITORIES = "AAAAAAAAAA";
    private static final String UPDATED_MUSICALWORKS_TERRITORIES = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_ITEM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_ITEM_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_PART_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_PART_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_SEASON_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_SEASON_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_RELATED_TOUR_ARTIST = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_RELATED_TOUR_ARTIST = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_MANUFACTURER_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_MANUFACTURER_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_MERCH_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_BRAND = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MERCH_LIMITED_EDITION = false;
    private static final Boolean UPDATED_MERCH_LIMITED_EDITION = true;

    private static final Boolean DEFAULT_MERCH_COLLECTORS_EDITION = false;
    private static final Boolean UPDATED_MERCH_COLLECTORS_EDITION = true;

    private static final Boolean DEFAULT_MERCH_OUT_OF_PRINT = false;
    private static final Boolean UPDATED_MERCH_OUT_OF_PRINT = true;

    private static final Boolean DEFAULT_MERCH_TOUR_MERCH = false;
    private static final Boolean UPDATED_MERCH_TOUR_MERCH = true;

    private static final String DEFAULT_MERCH_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_MERCH_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/assets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssetMockMvc;

    private Asset asset;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asset createEntity(EntityManager em) {
        Asset asset = new Asset()
            .title(DEFAULT_TITLE)
            .artist(DEFAULT_ARTIST)
            .type(DEFAULT_TYPE)
            .isrc(DEFAULT_ISRC)
            .recordingDuration(DEFAULT_RECORDING_DURATION)
            .recordingCyearCline(DEFAULT_RECORDING_CYEAR_CLINE)
            .recordingPyearPline(DEFAULT_RECORDING_PYEAR_PLINE)
            .recordingMainGenre(DEFAULT_RECORDING_MAIN_GENRE)
            .recordingMainGenreSub(DEFAULT_RECORDING_MAIN_GENRE_SUB)
            .recordingAlternativeGenre(DEFAULT_RECORDING_ALTERNATIVE_GENRE)
            .recordingAlternativeGenreSub(DEFAULT_RECORDING_ALTERNATIVE_GENRE_SUB)
            .recordingMasterOwner(DEFAULT_RECORDING_MASTER_OWNER)
            .recordingYearRecording(DEFAULT_RECORDING_YEAR_RECORDING)
            .recordingLocation(DEFAULT_RECORDING_LOCATION)
            .recordingLabelCountry(DEFAULT_RECORDING_LABEL_COUNTRY)
            .recordingBpm(DEFAULT_RECORDING_BPM)
            .recordingPublishers(DEFAULT_RECORDING_PUBLISHERS)
            .recordingLabelCopy(DEFAULT_RECORDING_LABEL_COPY)
            .recordingAdditionalLabelCopy(DEFAULT_RECORDING_ADDITIONAL_LABEL_COPY)
            .recordingDescription(DEFAULT_RECORDING_DESCRIPTION)
            .recordingTagWords(DEFAULT_RECORDING_TAG_WORDS)
            .recordingSuggestedUses(DEFAULT_RECORDING_SUGGESTED_USES)
            .musicalworksTypeOfWork(DEFAULT_MUSICALWORKS_TYPE_OF_WORK)
            .musicalworksVersionType(DEFAULT_MUSICALWORKS_VERSION_TYPE)
            .musicalworksDuration(DEFAULT_MUSICALWORKS_DURATION)
            .musicalworksLanguage(DEFAULT_MUSICALWORKS_LANGUAGE)
            .musicalworksCreationDate(DEFAULT_MUSICALWORKS_CREATION_DATE)
            .musicalworksPublicationDate(DEFAULT_MUSICALWORKS_PUBLICATION_DATE)
            .musicalworksRegistrationNumber(DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER)
            .musicalworksRegistrationDate(DEFAULT_MUSICALWORKS_REGISTRATION_DATE)
            .musicalworksUnclearedSample(DEFAULT_MUSICALWORKS_UNCLEARED_SAMPLE)
            .musicalworksControlled(DEFAULT_MUSICALWORKS_CONTROLLED)
            .musicalworksRelatedIsrcs(DEFAULT_MUSICALWORKS_RELATED_ISRCS)
            .musicalworksNotes(DEFAULT_MUSICALWORKS_NOTES)
            .musicalworksTerritory(DEFAULT_MUSICALWORKS_TERRITORY)
            .musicalworksCrcPublisher(DEFAULT_MUSICALWORKS_CRC_PUBLISHER)
            .musicalworksAdministrator(DEFAULT_MUSICALWORKS_ADMINISTRATOR)
            .musicalworksSubPublisher(DEFAULT_MUSICALWORKS_SUB_PUBLISHER)
            .musicalworksIncomeCollector(DEFAULT_MUSICALWORKS_INCOME_COLLECTOR)
            .musicalworksTerritories(DEFAULT_MUSICALWORKS_TERRITORIES)
            .merchItemType(DEFAULT_MERCH_ITEM_TYPE)
            .merchPartNumber(DEFAULT_MERCH_PART_NUMBER)
            .merchSeasonYear(DEFAULT_MERCH_SEASON_YEAR)
            .merchRelatedTourArtist(DEFAULT_MERCH_RELATED_TOUR_ARTIST)
            .merchManufacturerSupplier(DEFAULT_MERCH_MANUFACTURER_SUPPLIER)
            .merchBrand(DEFAULT_MERCH_BRAND)
            .merchLimitedEdition(DEFAULT_MERCH_LIMITED_EDITION)
            .merchCollectorsEdition(DEFAULT_MERCH_COLLECTORS_EDITION)
            .merchOutOfPrint(DEFAULT_MERCH_OUT_OF_PRINT)
            .merchTourMerch(DEFAULT_MERCH_TOUR_MERCH)
            .merchItemDescription(DEFAULT_MERCH_ITEM_DESCRIPTION);
        return asset;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asset createUpdatedEntity(EntityManager em) {
        Asset asset = new Asset()
            .title(UPDATED_TITLE)
            .artist(UPDATED_ARTIST)
            .type(UPDATED_TYPE)
            .isrc(UPDATED_ISRC)
            .recordingDuration(UPDATED_RECORDING_DURATION)
            .recordingCyearCline(UPDATED_RECORDING_CYEAR_CLINE)
            .recordingPyearPline(UPDATED_RECORDING_PYEAR_PLINE)
            .recordingMainGenre(UPDATED_RECORDING_MAIN_GENRE)
            .recordingMainGenreSub(UPDATED_RECORDING_MAIN_GENRE_SUB)
            .recordingAlternativeGenre(UPDATED_RECORDING_ALTERNATIVE_GENRE)
            .recordingAlternativeGenreSub(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB)
            .recordingMasterOwner(UPDATED_RECORDING_MASTER_OWNER)
            .recordingYearRecording(UPDATED_RECORDING_YEAR_RECORDING)
            .recordingLocation(UPDATED_RECORDING_LOCATION)
            .recordingLabelCountry(UPDATED_RECORDING_LABEL_COUNTRY)
            .recordingBpm(UPDATED_RECORDING_BPM)
            .recordingPublishers(UPDATED_RECORDING_PUBLISHERS)
            .recordingLabelCopy(UPDATED_RECORDING_LABEL_COPY)
            .recordingAdditionalLabelCopy(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY)
            .recordingDescription(UPDATED_RECORDING_DESCRIPTION)
            .recordingTagWords(UPDATED_RECORDING_TAG_WORDS)
            .recordingSuggestedUses(UPDATED_RECORDING_SUGGESTED_USES)
            .musicalworksTypeOfWork(UPDATED_MUSICALWORKS_TYPE_OF_WORK)
            .musicalworksVersionType(UPDATED_MUSICALWORKS_VERSION_TYPE)
            .musicalworksDuration(UPDATED_MUSICALWORKS_DURATION)
            .musicalworksLanguage(UPDATED_MUSICALWORKS_LANGUAGE)
            .musicalworksCreationDate(UPDATED_MUSICALWORKS_CREATION_DATE)
            .musicalworksPublicationDate(UPDATED_MUSICALWORKS_PUBLICATION_DATE)
            .musicalworksRegistrationNumber(UPDATED_MUSICALWORKS_REGISTRATION_NUMBER)
            .musicalworksRegistrationDate(UPDATED_MUSICALWORKS_REGISTRATION_DATE)
            .musicalworksUnclearedSample(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE)
            .musicalworksControlled(UPDATED_MUSICALWORKS_CONTROLLED)
            .musicalworksRelatedIsrcs(UPDATED_MUSICALWORKS_RELATED_ISRCS)
            .musicalworksNotes(UPDATED_MUSICALWORKS_NOTES)
            .musicalworksTerritory(UPDATED_MUSICALWORKS_TERRITORY)
            .musicalworksCrcPublisher(UPDATED_MUSICALWORKS_CRC_PUBLISHER)
            .musicalworksAdministrator(UPDATED_MUSICALWORKS_ADMINISTRATOR)
            .musicalworksSubPublisher(UPDATED_MUSICALWORKS_SUB_PUBLISHER)
            .musicalworksIncomeCollector(UPDATED_MUSICALWORKS_INCOME_COLLECTOR)
            .musicalworksTerritories(UPDATED_MUSICALWORKS_TERRITORIES)
            .merchItemType(UPDATED_MERCH_ITEM_TYPE)
            .merchPartNumber(UPDATED_MERCH_PART_NUMBER)
            .merchSeasonYear(UPDATED_MERCH_SEASON_YEAR)
            .merchRelatedTourArtist(UPDATED_MERCH_RELATED_TOUR_ARTIST)
            .merchManufacturerSupplier(UPDATED_MERCH_MANUFACTURER_SUPPLIER)
            .merchBrand(UPDATED_MERCH_BRAND)
            .merchLimitedEdition(UPDATED_MERCH_LIMITED_EDITION)
            .merchCollectorsEdition(UPDATED_MERCH_COLLECTORS_EDITION)
            .merchOutOfPrint(UPDATED_MERCH_OUT_OF_PRINT)
            .merchTourMerch(UPDATED_MERCH_TOUR_MERCH)
            .merchItemDescription(UPDATED_MERCH_ITEM_DESCRIPTION);
        return asset;
    }

    @BeforeEach
    public void initTest() {
        asset = createEntity(em);
    }

    @Test
    @Transactional
    void createAsset() throws Exception {
        int databaseSizeBeforeCreate = assetRepository.findAll().size();
        // Create the Asset
        restAssetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isCreated());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate + 1);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAsset.getArtist()).isEqualTo(DEFAULT_ARTIST);
        assertThat(testAsset.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAsset.getIsrc()).isEqualTo(DEFAULT_ISRC);
        assertThat(testAsset.getRecordingDuration()).isEqualTo(DEFAULT_RECORDING_DURATION);
        assertThat(testAsset.getRecordingCyearCline()).isEqualTo(DEFAULT_RECORDING_CYEAR_CLINE);
        assertThat(testAsset.getRecordingPyearPline()).isEqualTo(DEFAULT_RECORDING_PYEAR_PLINE);
        assertThat(testAsset.getRecordingMainGenre()).isEqualTo(DEFAULT_RECORDING_MAIN_GENRE);
        assertThat(testAsset.getRecordingMainGenreSub()).isEqualTo(DEFAULT_RECORDING_MAIN_GENRE_SUB);
        assertThat(testAsset.getRecordingAlternativeGenre()).isEqualTo(DEFAULT_RECORDING_ALTERNATIVE_GENRE);
        assertThat(testAsset.getRecordingAlternativeGenreSub()).isEqualTo(DEFAULT_RECORDING_ALTERNATIVE_GENRE_SUB);
        assertThat(testAsset.getRecordingMasterOwner()).isEqualTo(DEFAULT_RECORDING_MASTER_OWNER);
        assertThat(testAsset.getRecordingYearRecording()).isEqualTo(DEFAULT_RECORDING_YEAR_RECORDING);
        assertThat(testAsset.getRecordingLocation()).isEqualTo(DEFAULT_RECORDING_LOCATION);
        assertThat(testAsset.getRecordingLabelCountry()).isEqualTo(DEFAULT_RECORDING_LABEL_COUNTRY);
        assertThat(testAsset.getRecordingBpm()).isEqualTo(DEFAULT_RECORDING_BPM);
        assertThat(testAsset.getRecordingPublishers()).isEqualTo(DEFAULT_RECORDING_PUBLISHERS);
        assertThat(testAsset.getRecordingLabelCopy()).isEqualTo(DEFAULT_RECORDING_LABEL_COPY);
        assertThat(testAsset.getRecordingAdditionalLabelCopy()).isEqualTo(DEFAULT_RECORDING_ADDITIONAL_LABEL_COPY);
        assertThat(testAsset.getRecordingDescription()).isEqualTo(DEFAULT_RECORDING_DESCRIPTION);
        assertThat(testAsset.getRecordingTagWords()).isEqualTo(DEFAULT_RECORDING_TAG_WORDS);
        assertThat(testAsset.getRecordingSuggestedUses()).isEqualTo(DEFAULT_RECORDING_SUGGESTED_USES);
        assertThat(testAsset.getMusicalworksTypeOfWork()).isEqualTo(DEFAULT_MUSICALWORKS_TYPE_OF_WORK);
        assertThat(testAsset.getMusicalworksVersionType()).isEqualTo(DEFAULT_MUSICALWORKS_VERSION_TYPE);
        assertThat(testAsset.getMusicalworksDuration()).isEqualTo(DEFAULT_MUSICALWORKS_DURATION);
        assertThat(testAsset.getMusicalworksLanguage()).isEqualTo(DEFAULT_MUSICALWORKS_LANGUAGE);
        assertThat(testAsset.getMusicalworksCreationDate()).isEqualTo(DEFAULT_MUSICALWORKS_CREATION_DATE);
        assertThat(testAsset.getMusicalworksPublicationDate()).isEqualTo(DEFAULT_MUSICALWORKS_PUBLICATION_DATE);
        assertThat(testAsset.getMusicalworksRegistrationNumber()).isEqualTo(DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER);
        assertThat(testAsset.getMusicalworksRegistrationDate()).isEqualTo(DEFAULT_MUSICALWORKS_REGISTRATION_DATE);
        assertThat(testAsset.getMusicalworksUnclearedSample()).isEqualTo(DEFAULT_MUSICALWORKS_UNCLEARED_SAMPLE);
        assertThat(testAsset.getMusicalworksControlled()).isEqualTo(DEFAULT_MUSICALWORKS_CONTROLLED);
        assertThat(testAsset.getMusicalworksRelatedIsrcs()).isEqualTo(DEFAULT_MUSICALWORKS_RELATED_ISRCS);
        assertThat(testAsset.getMusicalworksNotes()).isEqualTo(DEFAULT_MUSICALWORKS_NOTES);
        assertThat(testAsset.getMusicalworksTerritory()).isEqualTo(DEFAULT_MUSICALWORKS_TERRITORY);
        assertThat(testAsset.getMusicalworksCrcPublisher()).isEqualTo(DEFAULT_MUSICALWORKS_CRC_PUBLISHER);
        assertThat(testAsset.getMusicalworksAdministrator()).isEqualTo(DEFAULT_MUSICALWORKS_ADMINISTRATOR);
        assertThat(testAsset.getMusicalworksSubPublisher()).isEqualTo(DEFAULT_MUSICALWORKS_SUB_PUBLISHER);
        assertThat(testAsset.getMusicalworksIncomeCollector()).isEqualTo(DEFAULT_MUSICALWORKS_INCOME_COLLECTOR);
        assertThat(testAsset.getMusicalworksTerritories()).isEqualTo(DEFAULT_MUSICALWORKS_TERRITORIES);
        assertThat(testAsset.getMerchItemType()).isEqualTo(DEFAULT_MERCH_ITEM_TYPE);
        assertThat(testAsset.getMerchPartNumber()).isEqualTo(DEFAULT_MERCH_PART_NUMBER);
        assertThat(testAsset.getMerchSeasonYear()).isEqualTo(DEFAULT_MERCH_SEASON_YEAR);
        assertThat(testAsset.getMerchRelatedTourArtist()).isEqualTo(DEFAULT_MERCH_RELATED_TOUR_ARTIST);
        assertThat(testAsset.getMerchManufacturerSupplier()).isEqualTo(DEFAULT_MERCH_MANUFACTURER_SUPPLIER);
        assertThat(testAsset.getMerchBrand()).isEqualTo(DEFAULT_MERCH_BRAND);
        assertThat(testAsset.getMerchLimitedEdition()).isEqualTo(DEFAULT_MERCH_LIMITED_EDITION);
        assertThat(testAsset.getMerchCollectorsEdition()).isEqualTo(DEFAULT_MERCH_COLLECTORS_EDITION);
        assertThat(testAsset.getMerchOutOfPrint()).isEqualTo(DEFAULT_MERCH_OUT_OF_PRINT);
        assertThat(testAsset.getMerchTourMerch()).isEqualTo(DEFAULT_MERCH_TOUR_MERCH);
        assertThat(testAsset.getMerchItemDescription()).isEqualTo(DEFAULT_MERCH_ITEM_DESCRIPTION);
    }

    @Test
    @Transactional
    void createAssetWithExistingId() throws Exception {
        // Create the Asset with an existing ID
        asset.setId(1L);

        int databaseSizeBeforeCreate = assetRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAssets() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList
        restAssetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asset.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].artist").value(hasItem(DEFAULT_ARTIST)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isrc").value(hasItem(DEFAULT_ISRC)))
            .andExpect(jsonPath("$.[*].recordingDuration").value(hasItem(DEFAULT_RECORDING_DURATION)))
            .andExpect(jsonPath("$.[*].recordingCyearCline").value(hasItem(DEFAULT_RECORDING_CYEAR_CLINE)))
            .andExpect(jsonPath("$.[*].recordingPyearPline").value(hasItem(DEFAULT_RECORDING_PYEAR_PLINE)))
            .andExpect(jsonPath("$.[*].recordingMainGenre").value(hasItem(DEFAULT_RECORDING_MAIN_GENRE)))
            .andExpect(jsonPath("$.[*].recordingMainGenreSub").value(hasItem(DEFAULT_RECORDING_MAIN_GENRE_SUB)))
            .andExpect(jsonPath("$.[*].recordingAlternativeGenre").value(hasItem(DEFAULT_RECORDING_ALTERNATIVE_GENRE)))
            .andExpect(jsonPath("$.[*].recordingAlternativeGenreSub").value(hasItem(DEFAULT_RECORDING_ALTERNATIVE_GENRE_SUB)))
            .andExpect(jsonPath("$.[*].recordingMasterOwner").value(hasItem(DEFAULT_RECORDING_MASTER_OWNER)))
            .andExpect(jsonPath("$.[*].recordingYearRecording").value(hasItem(DEFAULT_RECORDING_YEAR_RECORDING)))
            .andExpect(jsonPath("$.[*].recordingLocation").value(hasItem(DEFAULT_RECORDING_LOCATION)))
            .andExpect(jsonPath("$.[*].recordingLabelCountry").value(hasItem(DEFAULT_RECORDING_LABEL_COUNTRY)))
            .andExpect(jsonPath("$.[*].recordingBpm").value(hasItem(DEFAULT_RECORDING_BPM)))
            .andExpect(jsonPath("$.[*].recordingPublishers").value(hasItem(DEFAULT_RECORDING_PUBLISHERS)))
            .andExpect(jsonPath("$.[*].recordingLabelCopy").value(hasItem(DEFAULT_RECORDING_LABEL_COPY)))
            .andExpect(jsonPath("$.[*].recordingAdditionalLabelCopy").value(hasItem(DEFAULT_RECORDING_ADDITIONAL_LABEL_COPY)))
            .andExpect(jsonPath("$.[*].recordingDescription").value(hasItem(DEFAULT_RECORDING_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].recordingTagWords").value(hasItem(DEFAULT_RECORDING_TAG_WORDS)))
            .andExpect(jsonPath("$.[*].recordingSuggestedUses").value(hasItem(DEFAULT_RECORDING_SUGGESTED_USES)))
            .andExpect(jsonPath("$.[*].musicalworksTypeOfWork").value(hasItem(DEFAULT_MUSICALWORKS_TYPE_OF_WORK)))
            .andExpect(jsonPath("$.[*].musicalworksVersionType").value(hasItem(DEFAULT_MUSICALWORKS_VERSION_TYPE)))
            .andExpect(jsonPath("$.[*].musicalworksDuration").value(hasItem(DEFAULT_MUSICALWORKS_DURATION)))
            .andExpect(jsonPath("$.[*].musicalworksLanguage").value(hasItem(DEFAULT_MUSICALWORKS_LANGUAGE)))
            .andExpect(jsonPath("$.[*].musicalworksCreationDate").value(hasItem(DEFAULT_MUSICALWORKS_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].musicalworksPublicationDate").value(hasItem(DEFAULT_MUSICALWORKS_PUBLICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].musicalworksRegistrationNumber").value(hasItem(DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].musicalworksRegistrationDate").value(hasItem(DEFAULT_MUSICALWORKS_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].musicalworksUnclearedSample").value(hasItem(DEFAULT_MUSICALWORKS_UNCLEARED_SAMPLE.booleanValue())))
            .andExpect(jsonPath("$.[*].musicalworksControlled").value(hasItem(DEFAULT_MUSICALWORKS_CONTROLLED.booleanValue())))
            .andExpect(jsonPath("$.[*].musicalworksRelatedIsrcs").value(hasItem(DEFAULT_MUSICALWORKS_RELATED_ISRCS)))
            .andExpect(jsonPath("$.[*].musicalworksNotes").value(hasItem(DEFAULT_MUSICALWORKS_NOTES)))
            .andExpect(jsonPath("$.[*].musicalworksTerritory").value(hasItem(DEFAULT_MUSICALWORKS_TERRITORY)))
            .andExpect(jsonPath("$.[*].musicalworksCrcPublisher").value(hasItem(DEFAULT_MUSICALWORKS_CRC_PUBLISHER)))
            .andExpect(jsonPath("$.[*].musicalworksAdministrator").value(hasItem(DEFAULT_MUSICALWORKS_ADMINISTRATOR)))
            .andExpect(jsonPath("$.[*].musicalworksSubPublisher").value(hasItem(DEFAULT_MUSICALWORKS_SUB_PUBLISHER)))
            .andExpect(jsonPath("$.[*].musicalworksIncomeCollector").value(hasItem(DEFAULT_MUSICALWORKS_INCOME_COLLECTOR)))
            .andExpect(jsonPath("$.[*].musicalworksTerritories").value(hasItem(DEFAULT_MUSICALWORKS_TERRITORIES)))
            .andExpect(jsonPath("$.[*].merchItemType").value(hasItem(DEFAULT_MERCH_ITEM_TYPE)))
            .andExpect(jsonPath("$.[*].merchPartNumber").value(hasItem(DEFAULT_MERCH_PART_NUMBER)))
            .andExpect(jsonPath("$.[*].merchSeasonYear").value(hasItem(DEFAULT_MERCH_SEASON_YEAR)))
            .andExpect(jsonPath("$.[*].merchRelatedTourArtist").value(hasItem(DEFAULT_MERCH_RELATED_TOUR_ARTIST)))
            .andExpect(jsonPath("$.[*].merchManufacturerSupplier").value(hasItem(DEFAULT_MERCH_MANUFACTURER_SUPPLIER)))
            .andExpect(jsonPath("$.[*].merchBrand").value(hasItem(DEFAULT_MERCH_BRAND)))
            .andExpect(jsonPath("$.[*].merchLimitedEdition").value(hasItem(DEFAULT_MERCH_LIMITED_EDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].merchCollectorsEdition").value(hasItem(DEFAULT_MERCH_COLLECTORS_EDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].merchOutOfPrint").value(hasItem(DEFAULT_MERCH_OUT_OF_PRINT.booleanValue())))
            .andExpect(jsonPath("$.[*].merchTourMerch").value(hasItem(DEFAULT_MERCH_TOUR_MERCH.booleanValue())))
            .andExpect(jsonPath("$.[*].merchItemDescription").value(hasItem(DEFAULT_MERCH_ITEM_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get the asset
        restAssetMockMvc
            .perform(get(ENTITY_API_URL_ID, asset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(asset.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.artist").value(DEFAULT_ARTIST))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.isrc").value(DEFAULT_ISRC))
            .andExpect(jsonPath("$.recordingDuration").value(DEFAULT_RECORDING_DURATION))
            .andExpect(jsonPath("$.recordingCyearCline").value(DEFAULT_RECORDING_CYEAR_CLINE))
            .andExpect(jsonPath("$.recordingPyearPline").value(DEFAULT_RECORDING_PYEAR_PLINE))
            .andExpect(jsonPath("$.recordingMainGenre").value(DEFAULT_RECORDING_MAIN_GENRE))
            .andExpect(jsonPath("$.recordingMainGenreSub").value(DEFAULT_RECORDING_MAIN_GENRE_SUB))
            .andExpect(jsonPath("$.recordingAlternativeGenre").value(DEFAULT_RECORDING_ALTERNATIVE_GENRE))
            .andExpect(jsonPath("$.recordingAlternativeGenreSub").value(DEFAULT_RECORDING_ALTERNATIVE_GENRE_SUB))
            .andExpect(jsonPath("$.recordingMasterOwner").value(DEFAULT_RECORDING_MASTER_OWNER))
            .andExpect(jsonPath("$.recordingYearRecording").value(DEFAULT_RECORDING_YEAR_RECORDING))
            .andExpect(jsonPath("$.recordingLocation").value(DEFAULT_RECORDING_LOCATION))
            .andExpect(jsonPath("$.recordingLabelCountry").value(DEFAULT_RECORDING_LABEL_COUNTRY))
            .andExpect(jsonPath("$.recordingBpm").value(DEFAULT_RECORDING_BPM))
            .andExpect(jsonPath("$.recordingPublishers").value(DEFAULT_RECORDING_PUBLISHERS))
            .andExpect(jsonPath("$.recordingLabelCopy").value(DEFAULT_RECORDING_LABEL_COPY))
            .andExpect(jsonPath("$.recordingAdditionalLabelCopy").value(DEFAULT_RECORDING_ADDITIONAL_LABEL_COPY))
            .andExpect(jsonPath("$.recordingDescription").value(DEFAULT_RECORDING_DESCRIPTION))
            .andExpect(jsonPath("$.recordingTagWords").value(DEFAULT_RECORDING_TAG_WORDS))
            .andExpect(jsonPath("$.recordingSuggestedUses").value(DEFAULT_RECORDING_SUGGESTED_USES))
            .andExpect(jsonPath("$.musicalworksTypeOfWork").value(DEFAULT_MUSICALWORKS_TYPE_OF_WORK))
            .andExpect(jsonPath("$.musicalworksVersionType").value(DEFAULT_MUSICALWORKS_VERSION_TYPE))
            .andExpect(jsonPath("$.musicalworksDuration").value(DEFAULT_MUSICALWORKS_DURATION))
            .andExpect(jsonPath("$.musicalworksLanguage").value(DEFAULT_MUSICALWORKS_LANGUAGE))
            .andExpect(jsonPath("$.musicalworksCreationDate").value(DEFAULT_MUSICALWORKS_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.musicalworksPublicationDate").value(DEFAULT_MUSICALWORKS_PUBLICATION_DATE.toString()))
            .andExpect(jsonPath("$.musicalworksRegistrationNumber").value(DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.musicalworksRegistrationDate").value(DEFAULT_MUSICALWORKS_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.musicalworksUnclearedSample").value(DEFAULT_MUSICALWORKS_UNCLEARED_SAMPLE.booleanValue()))
            .andExpect(jsonPath("$.musicalworksControlled").value(DEFAULT_MUSICALWORKS_CONTROLLED.booleanValue()))
            .andExpect(jsonPath("$.musicalworksRelatedIsrcs").value(DEFAULT_MUSICALWORKS_RELATED_ISRCS))
            .andExpect(jsonPath("$.musicalworksNotes").value(DEFAULT_MUSICALWORKS_NOTES))
            .andExpect(jsonPath("$.musicalworksTerritory").value(DEFAULT_MUSICALWORKS_TERRITORY))
            .andExpect(jsonPath("$.musicalworksCrcPublisher").value(DEFAULT_MUSICALWORKS_CRC_PUBLISHER))
            .andExpect(jsonPath("$.musicalworksAdministrator").value(DEFAULT_MUSICALWORKS_ADMINISTRATOR))
            .andExpect(jsonPath("$.musicalworksSubPublisher").value(DEFAULT_MUSICALWORKS_SUB_PUBLISHER))
            .andExpect(jsonPath("$.musicalworksIncomeCollector").value(DEFAULT_MUSICALWORKS_INCOME_COLLECTOR))
            .andExpect(jsonPath("$.musicalworksTerritories").value(DEFAULT_MUSICALWORKS_TERRITORIES))
            .andExpect(jsonPath("$.merchItemType").value(DEFAULT_MERCH_ITEM_TYPE))
            .andExpect(jsonPath("$.merchPartNumber").value(DEFAULT_MERCH_PART_NUMBER))
            .andExpect(jsonPath("$.merchSeasonYear").value(DEFAULT_MERCH_SEASON_YEAR))
            .andExpect(jsonPath("$.merchRelatedTourArtist").value(DEFAULT_MERCH_RELATED_TOUR_ARTIST))
            .andExpect(jsonPath("$.merchManufacturerSupplier").value(DEFAULT_MERCH_MANUFACTURER_SUPPLIER))
            .andExpect(jsonPath("$.merchBrand").value(DEFAULT_MERCH_BRAND))
            .andExpect(jsonPath("$.merchLimitedEdition").value(DEFAULT_MERCH_LIMITED_EDITION.booleanValue()))
            .andExpect(jsonPath("$.merchCollectorsEdition").value(DEFAULT_MERCH_COLLECTORS_EDITION.booleanValue()))
            .andExpect(jsonPath("$.merchOutOfPrint").value(DEFAULT_MERCH_OUT_OF_PRINT.booleanValue()))
            .andExpect(jsonPath("$.merchTourMerch").value(DEFAULT_MERCH_TOUR_MERCH.booleanValue()))
            .andExpect(jsonPath("$.merchItemDescription").value(DEFAULT_MERCH_ITEM_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingAsset() throws Exception {
        // Get the asset
        restAssetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Update the asset
        Asset updatedAsset = assetRepository.findById(asset.getId()).get();
        // Disconnect from session so that the updates on updatedAsset are not directly saved in db
        em.detach(updatedAsset);
        updatedAsset
            .title(UPDATED_TITLE)
            .artist(UPDATED_ARTIST)
            .type(UPDATED_TYPE)
            .isrc(UPDATED_ISRC)
            .recordingDuration(UPDATED_RECORDING_DURATION)
            .recordingCyearCline(UPDATED_RECORDING_CYEAR_CLINE)
            .recordingPyearPline(UPDATED_RECORDING_PYEAR_PLINE)
            .recordingMainGenre(UPDATED_RECORDING_MAIN_GENRE)
            .recordingMainGenreSub(UPDATED_RECORDING_MAIN_GENRE_SUB)
            .recordingAlternativeGenre(UPDATED_RECORDING_ALTERNATIVE_GENRE)
            .recordingAlternativeGenreSub(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB)
            .recordingMasterOwner(UPDATED_RECORDING_MASTER_OWNER)
            .recordingYearRecording(UPDATED_RECORDING_YEAR_RECORDING)
            .recordingLocation(UPDATED_RECORDING_LOCATION)
            .recordingLabelCountry(UPDATED_RECORDING_LABEL_COUNTRY)
            .recordingBpm(UPDATED_RECORDING_BPM)
            .recordingPublishers(UPDATED_RECORDING_PUBLISHERS)
            .recordingLabelCopy(UPDATED_RECORDING_LABEL_COPY)
            .recordingAdditionalLabelCopy(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY)
            .recordingDescription(UPDATED_RECORDING_DESCRIPTION)
            .recordingTagWords(UPDATED_RECORDING_TAG_WORDS)
            .recordingSuggestedUses(UPDATED_RECORDING_SUGGESTED_USES)
            .musicalworksTypeOfWork(UPDATED_MUSICALWORKS_TYPE_OF_WORK)
            .musicalworksVersionType(UPDATED_MUSICALWORKS_VERSION_TYPE)
            .musicalworksDuration(UPDATED_MUSICALWORKS_DURATION)
            .musicalworksLanguage(UPDATED_MUSICALWORKS_LANGUAGE)
            .musicalworksCreationDate(UPDATED_MUSICALWORKS_CREATION_DATE)
            .musicalworksPublicationDate(UPDATED_MUSICALWORKS_PUBLICATION_DATE)
            .musicalworksRegistrationNumber(UPDATED_MUSICALWORKS_REGISTRATION_NUMBER)
            .musicalworksRegistrationDate(UPDATED_MUSICALWORKS_REGISTRATION_DATE)
            .musicalworksUnclearedSample(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE)
            .musicalworksControlled(UPDATED_MUSICALWORKS_CONTROLLED)
            .musicalworksRelatedIsrcs(UPDATED_MUSICALWORKS_RELATED_ISRCS)
            .musicalworksNotes(UPDATED_MUSICALWORKS_NOTES)
            .musicalworksTerritory(UPDATED_MUSICALWORKS_TERRITORY)
            .musicalworksCrcPublisher(UPDATED_MUSICALWORKS_CRC_PUBLISHER)
            .musicalworksAdministrator(UPDATED_MUSICALWORKS_ADMINISTRATOR)
            .musicalworksSubPublisher(UPDATED_MUSICALWORKS_SUB_PUBLISHER)
            .musicalworksIncomeCollector(UPDATED_MUSICALWORKS_INCOME_COLLECTOR)
            .musicalworksTerritories(UPDATED_MUSICALWORKS_TERRITORIES)
            .merchItemType(UPDATED_MERCH_ITEM_TYPE)
            .merchPartNumber(UPDATED_MERCH_PART_NUMBER)
            .merchSeasonYear(UPDATED_MERCH_SEASON_YEAR)
            .merchRelatedTourArtist(UPDATED_MERCH_RELATED_TOUR_ARTIST)
            .merchManufacturerSupplier(UPDATED_MERCH_MANUFACTURER_SUPPLIER)
            .merchBrand(UPDATED_MERCH_BRAND)
            .merchLimitedEdition(UPDATED_MERCH_LIMITED_EDITION)
            .merchCollectorsEdition(UPDATED_MERCH_COLLECTORS_EDITION)
            .merchOutOfPrint(UPDATED_MERCH_OUT_OF_PRINT)
            .merchTourMerch(UPDATED_MERCH_TOUR_MERCH)
            .merchItemDescription(UPDATED_MERCH_ITEM_DESCRIPTION);

        restAssetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAsset.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAsset))
            )
            .andExpect(status().isOk());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAsset.getArtist()).isEqualTo(UPDATED_ARTIST);
        assertThat(testAsset.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAsset.getIsrc()).isEqualTo(UPDATED_ISRC);
        assertThat(testAsset.getRecordingDuration()).isEqualTo(UPDATED_RECORDING_DURATION);
        assertThat(testAsset.getRecordingCyearCline()).isEqualTo(UPDATED_RECORDING_CYEAR_CLINE);
        assertThat(testAsset.getRecordingPyearPline()).isEqualTo(UPDATED_RECORDING_PYEAR_PLINE);
        assertThat(testAsset.getRecordingMainGenre()).isEqualTo(UPDATED_RECORDING_MAIN_GENRE);
        assertThat(testAsset.getRecordingMainGenreSub()).isEqualTo(UPDATED_RECORDING_MAIN_GENRE_SUB);
        assertThat(testAsset.getRecordingAlternativeGenre()).isEqualTo(UPDATED_RECORDING_ALTERNATIVE_GENRE);
        assertThat(testAsset.getRecordingAlternativeGenreSub()).isEqualTo(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB);
        assertThat(testAsset.getRecordingMasterOwner()).isEqualTo(UPDATED_RECORDING_MASTER_OWNER);
        assertThat(testAsset.getRecordingYearRecording()).isEqualTo(UPDATED_RECORDING_YEAR_RECORDING);
        assertThat(testAsset.getRecordingLocation()).isEqualTo(UPDATED_RECORDING_LOCATION);
        assertThat(testAsset.getRecordingLabelCountry()).isEqualTo(UPDATED_RECORDING_LABEL_COUNTRY);
        assertThat(testAsset.getRecordingBpm()).isEqualTo(UPDATED_RECORDING_BPM);
        assertThat(testAsset.getRecordingPublishers()).isEqualTo(UPDATED_RECORDING_PUBLISHERS);
        assertThat(testAsset.getRecordingLabelCopy()).isEqualTo(UPDATED_RECORDING_LABEL_COPY);
        assertThat(testAsset.getRecordingAdditionalLabelCopy()).isEqualTo(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY);
        assertThat(testAsset.getRecordingDescription()).isEqualTo(UPDATED_RECORDING_DESCRIPTION);
        assertThat(testAsset.getRecordingTagWords()).isEqualTo(UPDATED_RECORDING_TAG_WORDS);
        assertThat(testAsset.getRecordingSuggestedUses()).isEqualTo(UPDATED_RECORDING_SUGGESTED_USES);
        assertThat(testAsset.getMusicalworksTypeOfWork()).isEqualTo(UPDATED_MUSICALWORKS_TYPE_OF_WORK);
        assertThat(testAsset.getMusicalworksVersionType()).isEqualTo(UPDATED_MUSICALWORKS_VERSION_TYPE);
        assertThat(testAsset.getMusicalworksDuration()).isEqualTo(UPDATED_MUSICALWORKS_DURATION);
        assertThat(testAsset.getMusicalworksLanguage()).isEqualTo(UPDATED_MUSICALWORKS_LANGUAGE);
        assertThat(testAsset.getMusicalworksCreationDate()).isEqualTo(UPDATED_MUSICALWORKS_CREATION_DATE);
        assertThat(testAsset.getMusicalworksPublicationDate()).isEqualTo(UPDATED_MUSICALWORKS_PUBLICATION_DATE);
        assertThat(testAsset.getMusicalworksRegistrationNumber()).isEqualTo(UPDATED_MUSICALWORKS_REGISTRATION_NUMBER);
        assertThat(testAsset.getMusicalworksRegistrationDate()).isEqualTo(UPDATED_MUSICALWORKS_REGISTRATION_DATE);
        assertThat(testAsset.getMusicalworksUnclearedSample()).isEqualTo(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE);
        assertThat(testAsset.getMusicalworksControlled()).isEqualTo(UPDATED_MUSICALWORKS_CONTROLLED);
        assertThat(testAsset.getMusicalworksRelatedIsrcs()).isEqualTo(UPDATED_MUSICALWORKS_RELATED_ISRCS);
        assertThat(testAsset.getMusicalworksNotes()).isEqualTo(UPDATED_MUSICALWORKS_NOTES);
        assertThat(testAsset.getMusicalworksTerritory()).isEqualTo(UPDATED_MUSICALWORKS_TERRITORY);
        assertThat(testAsset.getMusicalworksCrcPublisher()).isEqualTo(UPDATED_MUSICALWORKS_CRC_PUBLISHER);
        assertThat(testAsset.getMusicalworksAdministrator()).isEqualTo(UPDATED_MUSICALWORKS_ADMINISTRATOR);
        assertThat(testAsset.getMusicalworksSubPublisher()).isEqualTo(UPDATED_MUSICALWORKS_SUB_PUBLISHER);
        assertThat(testAsset.getMusicalworksIncomeCollector()).isEqualTo(UPDATED_MUSICALWORKS_INCOME_COLLECTOR);
        assertThat(testAsset.getMusicalworksTerritories()).isEqualTo(UPDATED_MUSICALWORKS_TERRITORIES);
        assertThat(testAsset.getMerchItemType()).isEqualTo(UPDATED_MERCH_ITEM_TYPE);
        assertThat(testAsset.getMerchPartNumber()).isEqualTo(UPDATED_MERCH_PART_NUMBER);
        assertThat(testAsset.getMerchSeasonYear()).isEqualTo(UPDATED_MERCH_SEASON_YEAR);
        assertThat(testAsset.getMerchRelatedTourArtist()).isEqualTo(UPDATED_MERCH_RELATED_TOUR_ARTIST);
        assertThat(testAsset.getMerchManufacturerSupplier()).isEqualTo(UPDATED_MERCH_MANUFACTURER_SUPPLIER);
        assertThat(testAsset.getMerchBrand()).isEqualTo(UPDATED_MERCH_BRAND);
        assertThat(testAsset.getMerchLimitedEdition()).isEqualTo(UPDATED_MERCH_LIMITED_EDITION);
        assertThat(testAsset.getMerchCollectorsEdition()).isEqualTo(UPDATED_MERCH_COLLECTORS_EDITION);
        assertThat(testAsset.getMerchOutOfPrint()).isEqualTo(UPDATED_MERCH_OUT_OF_PRINT);
        assertThat(testAsset.getMerchTourMerch()).isEqualTo(UPDATED_MERCH_TOUR_MERCH);
        assertThat(testAsset.getMerchItemDescription()).isEqualTo(UPDATED_MERCH_ITEM_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, asset.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asset))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(asset))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAssetWithPatch() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Update the asset using partial update
        Asset partialUpdatedAsset = new Asset();
        partialUpdatedAsset.setId(asset.getId());

        partialUpdatedAsset
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .recordingDuration(UPDATED_RECORDING_DURATION)
            .recordingCyearCline(UPDATED_RECORDING_CYEAR_CLINE)
            .recordingMainGenreSub(UPDATED_RECORDING_MAIN_GENRE_SUB)
            .recordingAlternativeGenreSub(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB)
            .recordingYearRecording(UPDATED_RECORDING_YEAR_RECORDING)
            .recordingLabelCountry(UPDATED_RECORDING_LABEL_COUNTRY)
            .recordingPublishers(UPDATED_RECORDING_PUBLISHERS)
            .recordingAdditionalLabelCopy(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY)
            .recordingDescription(UPDATED_RECORDING_DESCRIPTION)
            .recordingTagWords(UPDATED_RECORDING_TAG_WORDS)
            .musicalworksTypeOfWork(UPDATED_MUSICALWORKS_TYPE_OF_WORK)
            .musicalworksDuration(UPDATED_MUSICALWORKS_DURATION)
            .musicalworksLanguage(UPDATED_MUSICALWORKS_LANGUAGE)
            .musicalworksCreationDate(UPDATED_MUSICALWORKS_CREATION_DATE)
            .musicalworksPublicationDate(UPDATED_MUSICALWORKS_PUBLICATION_DATE)
            .musicalworksRegistrationDate(UPDATED_MUSICALWORKS_REGISTRATION_DATE)
            .musicalworksUnclearedSample(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE)
            .musicalworksControlled(UPDATED_MUSICALWORKS_CONTROLLED)
            .musicalworksRelatedIsrcs(UPDATED_MUSICALWORKS_RELATED_ISRCS)
            .musicalworksAdministrator(UPDATED_MUSICALWORKS_ADMINISTRATOR)
            .musicalworksTerritories(UPDATED_MUSICALWORKS_TERRITORIES)
            .merchLimitedEdition(UPDATED_MERCH_LIMITED_EDITION)
            .merchOutOfPrint(UPDATED_MERCH_OUT_OF_PRINT);

        restAssetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsset.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsset))
            )
            .andExpect(status().isOk());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAsset.getArtist()).isEqualTo(DEFAULT_ARTIST);
        assertThat(testAsset.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAsset.getIsrc()).isEqualTo(DEFAULT_ISRC);
        assertThat(testAsset.getRecordingDuration()).isEqualTo(UPDATED_RECORDING_DURATION);
        assertThat(testAsset.getRecordingCyearCline()).isEqualTo(UPDATED_RECORDING_CYEAR_CLINE);
        assertThat(testAsset.getRecordingPyearPline()).isEqualTo(DEFAULT_RECORDING_PYEAR_PLINE);
        assertThat(testAsset.getRecordingMainGenre()).isEqualTo(DEFAULT_RECORDING_MAIN_GENRE);
        assertThat(testAsset.getRecordingMainGenreSub()).isEqualTo(UPDATED_RECORDING_MAIN_GENRE_SUB);
        assertThat(testAsset.getRecordingAlternativeGenre()).isEqualTo(DEFAULT_RECORDING_ALTERNATIVE_GENRE);
        assertThat(testAsset.getRecordingAlternativeGenreSub()).isEqualTo(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB);
        assertThat(testAsset.getRecordingMasterOwner()).isEqualTo(DEFAULT_RECORDING_MASTER_OWNER);
        assertThat(testAsset.getRecordingYearRecording()).isEqualTo(UPDATED_RECORDING_YEAR_RECORDING);
        assertThat(testAsset.getRecordingLocation()).isEqualTo(DEFAULT_RECORDING_LOCATION);
        assertThat(testAsset.getRecordingLabelCountry()).isEqualTo(UPDATED_RECORDING_LABEL_COUNTRY);
        assertThat(testAsset.getRecordingBpm()).isEqualTo(DEFAULT_RECORDING_BPM);
        assertThat(testAsset.getRecordingPublishers()).isEqualTo(UPDATED_RECORDING_PUBLISHERS);
        assertThat(testAsset.getRecordingLabelCopy()).isEqualTo(DEFAULT_RECORDING_LABEL_COPY);
        assertThat(testAsset.getRecordingAdditionalLabelCopy()).isEqualTo(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY);
        assertThat(testAsset.getRecordingDescription()).isEqualTo(UPDATED_RECORDING_DESCRIPTION);
        assertThat(testAsset.getRecordingTagWords()).isEqualTo(UPDATED_RECORDING_TAG_WORDS);
        assertThat(testAsset.getRecordingSuggestedUses()).isEqualTo(DEFAULT_RECORDING_SUGGESTED_USES);
        assertThat(testAsset.getMusicalworksTypeOfWork()).isEqualTo(UPDATED_MUSICALWORKS_TYPE_OF_WORK);
        assertThat(testAsset.getMusicalworksVersionType()).isEqualTo(DEFAULT_MUSICALWORKS_VERSION_TYPE);
        assertThat(testAsset.getMusicalworksDuration()).isEqualTo(UPDATED_MUSICALWORKS_DURATION);
        assertThat(testAsset.getMusicalworksLanguage()).isEqualTo(UPDATED_MUSICALWORKS_LANGUAGE);
        assertThat(testAsset.getMusicalworksCreationDate()).isEqualTo(UPDATED_MUSICALWORKS_CREATION_DATE);
        assertThat(testAsset.getMusicalworksPublicationDate()).isEqualTo(UPDATED_MUSICALWORKS_PUBLICATION_DATE);
        assertThat(testAsset.getMusicalworksRegistrationNumber()).isEqualTo(DEFAULT_MUSICALWORKS_REGISTRATION_NUMBER);
        assertThat(testAsset.getMusicalworksRegistrationDate()).isEqualTo(UPDATED_MUSICALWORKS_REGISTRATION_DATE);
        assertThat(testAsset.getMusicalworksUnclearedSample()).isEqualTo(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE);
        assertThat(testAsset.getMusicalworksControlled()).isEqualTo(UPDATED_MUSICALWORKS_CONTROLLED);
        assertThat(testAsset.getMusicalworksRelatedIsrcs()).isEqualTo(UPDATED_MUSICALWORKS_RELATED_ISRCS);
        assertThat(testAsset.getMusicalworksNotes()).isEqualTo(DEFAULT_MUSICALWORKS_NOTES);
        assertThat(testAsset.getMusicalworksTerritory()).isEqualTo(DEFAULT_MUSICALWORKS_TERRITORY);
        assertThat(testAsset.getMusicalworksCrcPublisher()).isEqualTo(DEFAULT_MUSICALWORKS_CRC_PUBLISHER);
        assertThat(testAsset.getMusicalworksAdministrator()).isEqualTo(UPDATED_MUSICALWORKS_ADMINISTRATOR);
        assertThat(testAsset.getMusicalworksSubPublisher()).isEqualTo(DEFAULT_MUSICALWORKS_SUB_PUBLISHER);
        assertThat(testAsset.getMusicalworksIncomeCollector()).isEqualTo(DEFAULT_MUSICALWORKS_INCOME_COLLECTOR);
        assertThat(testAsset.getMusicalworksTerritories()).isEqualTo(UPDATED_MUSICALWORKS_TERRITORIES);
        assertThat(testAsset.getMerchItemType()).isEqualTo(DEFAULT_MERCH_ITEM_TYPE);
        assertThat(testAsset.getMerchPartNumber()).isEqualTo(DEFAULT_MERCH_PART_NUMBER);
        assertThat(testAsset.getMerchSeasonYear()).isEqualTo(DEFAULT_MERCH_SEASON_YEAR);
        assertThat(testAsset.getMerchRelatedTourArtist()).isEqualTo(DEFAULT_MERCH_RELATED_TOUR_ARTIST);
        assertThat(testAsset.getMerchManufacturerSupplier()).isEqualTo(DEFAULT_MERCH_MANUFACTURER_SUPPLIER);
        assertThat(testAsset.getMerchBrand()).isEqualTo(DEFAULT_MERCH_BRAND);
        assertThat(testAsset.getMerchLimitedEdition()).isEqualTo(UPDATED_MERCH_LIMITED_EDITION);
        assertThat(testAsset.getMerchCollectorsEdition()).isEqualTo(DEFAULT_MERCH_COLLECTORS_EDITION);
        assertThat(testAsset.getMerchOutOfPrint()).isEqualTo(UPDATED_MERCH_OUT_OF_PRINT);
        assertThat(testAsset.getMerchTourMerch()).isEqualTo(DEFAULT_MERCH_TOUR_MERCH);
        assertThat(testAsset.getMerchItemDescription()).isEqualTo(DEFAULT_MERCH_ITEM_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateAssetWithPatch() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Update the asset using partial update
        Asset partialUpdatedAsset = new Asset();
        partialUpdatedAsset.setId(asset.getId());

        partialUpdatedAsset
            .title(UPDATED_TITLE)
            .artist(UPDATED_ARTIST)
            .type(UPDATED_TYPE)
            .isrc(UPDATED_ISRC)
            .recordingDuration(UPDATED_RECORDING_DURATION)
            .recordingCyearCline(UPDATED_RECORDING_CYEAR_CLINE)
            .recordingPyearPline(UPDATED_RECORDING_PYEAR_PLINE)
            .recordingMainGenre(UPDATED_RECORDING_MAIN_GENRE)
            .recordingMainGenreSub(UPDATED_RECORDING_MAIN_GENRE_SUB)
            .recordingAlternativeGenre(UPDATED_RECORDING_ALTERNATIVE_GENRE)
            .recordingAlternativeGenreSub(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB)
            .recordingMasterOwner(UPDATED_RECORDING_MASTER_OWNER)
            .recordingYearRecording(UPDATED_RECORDING_YEAR_RECORDING)
            .recordingLocation(UPDATED_RECORDING_LOCATION)
            .recordingLabelCountry(UPDATED_RECORDING_LABEL_COUNTRY)
            .recordingBpm(UPDATED_RECORDING_BPM)
            .recordingPublishers(UPDATED_RECORDING_PUBLISHERS)
            .recordingLabelCopy(UPDATED_RECORDING_LABEL_COPY)
            .recordingAdditionalLabelCopy(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY)
            .recordingDescription(UPDATED_RECORDING_DESCRIPTION)
            .recordingTagWords(UPDATED_RECORDING_TAG_WORDS)
            .recordingSuggestedUses(UPDATED_RECORDING_SUGGESTED_USES)
            .musicalworksTypeOfWork(UPDATED_MUSICALWORKS_TYPE_OF_WORK)
            .musicalworksVersionType(UPDATED_MUSICALWORKS_VERSION_TYPE)
            .musicalworksDuration(UPDATED_MUSICALWORKS_DURATION)
            .musicalworksLanguage(UPDATED_MUSICALWORKS_LANGUAGE)
            .musicalworksCreationDate(UPDATED_MUSICALWORKS_CREATION_DATE)
            .musicalworksPublicationDate(UPDATED_MUSICALWORKS_PUBLICATION_DATE)
            .musicalworksRegistrationNumber(UPDATED_MUSICALWORKS_REGISTRATION_NUMBER)
            .musicalworksRegistrationDate(UPDATED_MUSICALWORKS_REGISTRATION_DATE)
            .musicalworksUnclearedSample(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE)
            .musicalworksControlled(UPDATED_MUSICALWORKS_CONTROLLED)
            .musicalworksRelatedIsrcs(UPDATED_MUSICALWORKS_RELATED_ISRCS)
            .musicalworksNotes(UPDATED_MUSICALWORKS_NOTES)
            .musicalworksTerritory(UPDATED_MUSICALWORKS_TERRITORY)
            .musicalworksCrcPublisher(UPDATED_MUSICALWORKS_CRC_PUBLISHER)
            .musicalworksAdministrator(UPDATED_MUSICALWORKS_ADMINISTRATOR)
            .musicalworksSubPublisher(UPDATED_MUSICALWORKS_SUB_PUBLISHER)
            .musicalworksIncomeCollector(UPDATED_MUSICALWORKS_INCOME_COLLECTOR)
            .musicalworksTerritories(UPDATED_MUSICALWORKS_TERRITORIES)
            .merchItemType(UPDATED_MERCH_ITEM_TYPE)
            .merchPartNumber(UPDATED_MERCH_PART_NUMBER)
            .merchSeasonYear(UPDATED_MERCH_SEASON_YEAR)
            .merchRelatedTourArtist(UPDATED_MERCH_RELATED_TOUR_ARTIST)
            .merchManufacturerSupplier(UPDATED_MERCH_MANUFACTURER_SUPPLIER)
            .merchBrand(UPDATED_MERCH_BRAND)
            .merchLimitedEdition(UPDATED_MERCH_LIMITED_EDITION)
            .merchCollectorsEdition(UPDATED_MERCH_COLLECTORS_EDITION)
            .merchOutOfPrint(UPDATED_MERCH_OUT_OF_PRINT)
            .merchTourMerch(UPDATED_MERCH_TOUR_MERCH)
            .merchItemDescription(UPDATED_MERCH_ITEM_DESCRIPTION);

        restAssetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAsset.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAsset))
            )
            .andExpect(status().isOk());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAsset.getArtist()).isEqualTo(UPDATED_ARTIST);
        assertThat(testAsset.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAsset.getIsrc()).isEqualTo(UPDATED_ISRC);
        assertThat(testAsset.getRecordingDuration()).isEqualTo(UPDATED_RECORDING_DURATION);
        assertThat(testAsset.getRecordingCyearCline()).isEqualTo(UPDATED_RECORDING_CYEAR_CLINE);
        assertThat(testAsset.getRecordingPyearPline()).isEqualTo(UPDATED_RECORDING_PYEAR_PLINE);
        assertThat(testAsset.getRecordingMainGenre()).isEqualTo(UPDATED_RECORDING_MAIN_GENRE);
        assertThat(testAsset.getRecordingMainGenreSub()).isEqualTo(UPDATED_RECORDING_MAIN_GENRE_SUB);
        assertThat(testAsset.getRecordingAlternativeGenre()).isEqualTo(UPDATED_RECORDING_ALTERNATIVE_GENRE);
        assertThat(testAsset.getRecordingAlternativeGenreSub()).isEqualTo(UPDATED_RECORDING_ALTERNATIVE_GENRE_SUB);
        assertThat(testAsset.getRecordingMasterOwner()).isEqualTo(UPDATED_RECORDING_MASTER_OWNER);
        assertThat(testAsset.getRecordingYearRecording()).isEqualTo(UPDATED_RECORDING_YEAR_RECORDING);
        assertThat(testAsset.getRecordingLocation()).isEqualTo(UPDATED_RECORDING_LOCATION);
        assertThat(testAsset.getRecordingLabelCountry()).isEqualTo(UPDATED_RECORDING_LABEL_COUNTRY);
        assertThat(testAsset.getRecordingBpm()).isEqualTo(UPDATED_RECORDING_BPM);
        assertThat(testAsset.getRecordingPublishers()).isEqualTo(UPDATED_RECORDING_PUBLISHERS);
        assertThat(testAsset.getRecordingLabelCopy()).isEqualTo(UPDATED_RECORDING_LABEL_COPY);
        assertThat(testAsset.getRecordingAdditionalLabelCopy()).isEqualTo(UPDATED_RECORDING_ADDITIONAL_LABEL_COPY);
        assertThat(testAsset.getRecordingDescription()).isEqualTo(UPDATED_RECORDING_DESCRIPTION);
        assertThat(testAsset.getRecordingTagWords()).isEqualTo(UPDATED_RECORDING_TAG_WORDS);
        assertThat(testAsset.getRecordingSuggestedUses()).isEqualTo(UPDATED_RECORDING_SUGGESTED_USES);
        assertThat(testAsset.getMusicalworksTypeOfWork()).isEqualTo(UPDATED_MUSICALWORKS_TYPE_OF_WORK);
        assertThat(testAsset.getMusicalworksVersionType()).isEqualTo(UPDATED_MUSICALWORKS_VERSION_TYPE);
        assertThat(testAsset.getMusicalworksDuration()).isEqualTo(UPDATED_MUSICALWORKS_DURATION);
        assertThat(testAsset.getMusicalworksLanguage()).isEqualTo(UPDATED_MUSICALWORKS_LANGUAGE);
        assertThat(testAsset.getMusicalworksCreationDate()).isEqualTo(UPDATED_MUSICALWORKS_CREATION_DATE);
        assertThat(testAsset.getMusicalworksPublicationDate()).isEqualTo(UPDATED_MUSICALWORKS_PUBLICATION_DATE);
        assertThat(testAsset.getMusicalworksRegistrationNumber()).isEqualTo(UPDATED_MUSICALWORKS_REGISTRATION_NUMBER);
        assertThat(testAsset.getMusicalworksRegistrationDate()).isEqualTo(UPDATED_MUSICALWORKS_REGISTRATION_DATE);
        assertThat(testAsset.getMusicalworksUnclearedSample()).isEqualTo(UPDATED_MUSICALWORKS_UNCLEARED_SAMPLE);
        assertThat(testAsset.getMusicalworksControlled()).isEqualTo(UPDATED_MUSICALWORKS_CONTROLLED);
        assertThat(testAsset.getMusicalworksRelatedIsrcs()).isEqualTo(UPDATED_MUSICALWORKS_RELATED_ISRCS);
        assertThat(testAsset.getMusicalworksNotes()).isEqualTo(UPDATED_MUSICALWORKS_NOTES);
        assertThat(testAsset.getMusicalworksTerritory()).isEqualTo(UPDATED_MUSICALWORKS_TERRITORY);
        assertThat(testAsset.getMusicalworksCrcPublisher()).isEqualTo(UPDATED_MUSICALWORKS_CRC_PUBLISHER);
        assertThat(testAsset.getMusicalworksAdministrator()).isEqualTo(UPDATED_MUSICALWORKS_ADMINISTRATOR);
        assertThat(testAsset.getMusicalworksSubPublisher()).isEqualTo(UPDATED_MUSICALWORKS_SUB_PUBLISHER);
        assertThat(testAsset.getMusicalworksIncomeCollector()).isEqualTo(UPDATED_MUSICALWORKS_INCOME_COLLECTOR);
        assertThat(testAsset.getMusicalworksTerritories()).isEqualTo(UPDATED_MUSICALWORKS_TERRITORIES);
        assertThat(testAsset.getMerchItemType()).isEqualTo(UPDATED_MERCH_ITEM_TYPE);
        assertThat(testAsset.getMerchPartNumber()).isEqualTo(UPDATED_MERCH_PART_NUMBER);
        assertThat(testAsset.getMerchSeasonYear()).isEqualTo(UPDATED_MERCH_SEASON_YEAR);
        assertThat(testAsset.getMerchRelatedTourArtist()).isEqualTo(UPDATED_MERCH_RELATED_TOUR_ARTIST);
        assertThat(testAsset.getMerchManufacturerSupplier()).isEqualTo(UPDATED_MERCH_MANUFACTURER_SUPPLIER);
        assertThat(testAsset.getMerchBrand()).isEqualTo(UPDATED_MERCH_BRAND);
        assertThat(testAsset.getMerchLimitedEdition()).isEqualTo(UPDATED_MERCH_LIMITED_EDITION);
        assertThat(testAsset.getMerchCollectorsEdition()).isEqualTo(UPDATED_MERCH_COLLECTORS_EDITION);
        assertThat(testAsset.getMerchOutOfPrint()).isEqualTo(UPDATED_MERCH_OUT_OF_PRINT);
        assertThat(testAsset.getMerchTourMerch()).isEqualTo(UPDATED_MERCH_TOUR_MERCH);
        assertThat(testAsset.getMerchItemDescription()).isEqualTo(UPDATED_MERCH_ITEM_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, asset.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asset))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(asset))
            )
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();
        asset.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(asset)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeDelete = assetRepository.findAll().size();

        // Delete the asset
        restAssetMockMvc
            .perform(delete(ENTITY_API_URL_ID, asset.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
