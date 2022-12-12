package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Asset;
import com.mycompany.myapp.repository.AssetRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Asset}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AssetResource {

    private final Logger log = LoggerFactory.getLogger(AssetResource.class);

    private static final String ENTITY_NAME = "asset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssetRepository assetRepository;

    public AssetResource(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * {@code POST  /assets} : Create a new asset.
     *
     * @param asset the asset to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new asset, or with status {@code 400 (Bad Request)} if the asset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) throws URISyntaxException {
        log.debug("REST request to save Asset : {}", asset);
        if (asset.getId() != null) {
            throw new BadRequestAlertException("A new asset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Asset result = assetRepository.save(asset);
        return ResponseEntity
            .created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assets/:id} : Updates an existing asset.
     *
     * @param id the id of the asset to save.
     * @param asset the asset to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated asset,
     * or with status {@code 400 (Bad Request)} if the asset is not valid,
     * or with status {@code 500 (Internal Server Error)} if the asset couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assets/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable(value = "id", required = false) final Long id, @RequestBody Asset asset)
        throws URISyntaxException {
        log.debug("REST request to update Asset : {}, {}", id, asset);
        if (asset.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, asset.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Asset result = assetRepository.save(asset);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, asset.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /assets/:id} : Partial updates given fields of an existing asset, field will ignore if it is null
     *
     * @param id the id of the asset to save.
     * @param asset the asset to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated asset,
     * or with status {@code 400 (Bad Request)} if the asset is not valid,
     * or with status {@code 404 (Not Found)} if the asset is not found,
     * or with status {@code 500 (Internal Server Error)} if the asset couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/assets/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Asset> partialUpdateAsset(@PathVariable(value = "id", required = false) final Long id, @RequestBody Asset asset)
        throws URISyntaxException {
        log.debug("REST request to partial update Asset partially : {}, {}", id, asset);
        if (asset.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, asset.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Asset> result = assetRepository
            .findById(asset.getId())
            .map(existingAsset -> {
                if (asset.getTitle() != null) {
                    existingAsset.setTitle(asset.getTitle());
                }
                if (asset.getArtist() != null) {
                    existingAsset.setArtist(asset.getArtist());
                }
                if (asset.getType() != null) {
                    existingAsset.setType(asset.getType());
                }
                if (asset.getIsrc() != null) {
                    existingAsset.setIsrc(asset.getIsrc());
                }
                if (asset.getRecordingDuration() != null) {
                    existingAsset.setRecordingDuration(asset.getRecordingDuration());
                }
                if (asset.getRecordingCyearCline() != null) {
                    existingAsset.setRecordingCyearCline(asset.getRecordingCyearCline());
                }
                if (asset.getRecordingPyearPline() != null) {
                    existingAsset.setRecordingPyearPline(asset.getRecordingPyearPline());
                }
                if (asset.getRecordingMainGenre() != null) {
                    existingAsset.setRecordingMainGenre(asset.getRecordingMainGenre());
                }
                if (asset.getRecordingMainGenreSub() != null) {
                    existingAsset.setRecordingMainGenreSub(asset.getRecordingMainGenreSub());
                }
                if (asset.getRecordingAlternativeGenre() != null) {
                    existingAsset.setRecordingAlternativeGenre(asset.getRecordingAlternativeGenre());
                }
                if (asset.getRecordingAlternativeGenreSub() != null) {
                    existingAsset.setRecordingAlternativeGenreSub(asset.getRecordingAlternativeGenreSub());
                }
                if (asset.getRecordingMasterOwner() != null) {
                    existingAsset.setRecordingMasterOwner(asset.getRecordingMasterOwner());
                }
                if (asset.getRecordingYearRecording() != null) {
                    existingAsset.setRecordingYearRecording(asset.getRecordingYearRecording());
                }
                if (asset.getRecordingLocation() != null) {
                    existingAsset.setRecordingLocation(asset.getRecordingLocation());
                }
                if (asset.getRecordingLabelCountry() != null) {
                    existingAsset.setRecordingLabelCountry(asset.getRecordingLabelCountry());
                }
                if (asset.getRecordingBpm() != null) {
                    existingAsset.setRecordingBpm(asset.getRecordingBpm());
                }
                if (asset.getRecordingPublishers() != null) {
                    existingAsset.setRecordingPublishers(asset.getRecordingPublishers());
                }
                if (asset.getRecordingLabelCopy() != null) {
                    existingAsset.setRecordingLabelCopy(asset.getRecordingLabelCopy());
                }
                if (asset.getRecordingAdditionalLabelCopy() != null) {
                    existingAsset.setRecordingAdditionalLabelCopy(asset.getRecordingAdditionalLabelCopy());
                }
                if (asset.getRecordingDescription() != null) {
                    existingAsset.setRecordingDescription(asset.getRecordingDescription());
                }
                if (asset.getRecordingTagWords() != null) {
                    existingAsset.setRecordingTagWords(asset.getRecordingTagWords());
                }
                if (asset.getRecordingSuggestedUses() != null) {
                    existingAsset.setRecordingSuggestedUses(asset.getRecordingSuggestedUses());
                }
                if (asset.getMusicalworksTypeOfWork() != null) {
                    existingAsset.setMusicalworksTypeOfWork(asset.getMusicalworksTypeOfWork());
                }
                if (asset.getMusicalworksVersionType() != null) {
                    existingAsset.setMusicalworksVersionType(asset.getMusicalworksVersionType());
                }
                if (asset.getMusicalworksDuration() != null) {
                    existingAsset.setMusicalworksDuration(asset.getMusicalworksDuration());
                }
                if (asset.getMusicalworksLanguage() != null) {
                    existingAsset.setMusicalworksLanguage(asset.getMusicalworksLanguage());
                }
                if (asset.getMusicalworksCreationDate() != null) {
                    existingAsset.setMusicalworksCreationDate(asset.getMusicalworksCreationDate());
                }
                if (asset.getMusicalworksPublicationDate() != null) {
                    existingAsset.setMusicalworksPublicationDate(asset.getMusicalworksPublicationDate());
                }
                if (asset.getMusicalworksRegistrationNumber() != null) {
                    existingAsset.setMusicalworksRegistrationNumber(asset.getMusicalworksRegistrationNumber());
                }
                if (asset.getMusicalworksRegistrationDate() != null) {
                    existingAsset.setMusicalworksRegistrationDate(asset.getMusicalworksRegistrationDate());
                }
                if (asset.getMusicalworksUnclearedSample() != null) {
                    existingAsset.setMusicalworksUnclearedSample(asset.getMusicalworksUnclearedSample());
                }
                if (asset.getMusicalworksControlled() != null) {
                    existingAsset.setMusicalworksControlled(asset.getMusicalworksControlled());
                }
                if (asset.getMusicalworksRelatedIsrcs() != null) {
                    existingAsset.setMusicalworksRelatedIsrcs(asset.getMusicalworksRelatedIsrcs());
                }
                if (asset.getMusicalworksNotes() != null) {
                    existingAsset.setMusicalworksNotes(asset.getMusicalworksNotes());
                }
                if (asset.getMusicalworksTerritory() != null) {
                    existingAsset.setMusicalworksTerritory(asset.getMusicalworksTerritory());
                }
                if (asset.getMusicalworksCrcPublisher() != null) {
                    existingAsset.setMusicalworksCrcPublisher(asset.getMusicalworksCrcPublisher());
                }
                if (asset.getMusicalworksAdministrator() != null) {
                    existingAsset.setMusicalworksAdministrator(asset.getMusicalworksAdministrator());
                }
                if (asset.getMusicalworksSubPublisher() != null) {
                    existingAsset.setMusicalworksSubPublisher(asset.getMusicalworksSubPublisher());
                }
                if (asset.getMusicalworksIncomeCollector() != null) {
                    existingAsset.setMusicalworksIncomeCollector(asset.getMusicalworksIncomeCollector());
                }
                if (asset.getMusicalworksTerritories() != null) {
                    existingAsset.setMusicalworksTerritories(asset.getMusicalworksTerritories());
                }
                if (asset.getMerchItemType() != null) {
                    existingAsset.setMerchItemType(asset.getMerchItemType());
                }
                if (asset.getMerchPartNumber() != null) {
                    existingAsset.setMerchPartNumber(asset.getMerchPartNumber());
                }
                if (asset.getMerchSeasonYear() != null) {
                    existingAsset.setMerchSeasonYear(asset.getMerchSeasonYear());
                }
                if (asset.getMerchRelatedTourArtist() != null) {
                    existingAsset.setMerchRelatedTourArtist(asset.getMerchRelatedTourArtist());
                }
                if (asset.getMerchManufacturerSupplier() != null) {
                    existingAsset.setMerchManufacturerSupplier(asset.getMerchManufacturerSupplier());
                }
                if (asset.getMerchBrand() != null) {
                    existingAsset.setMerchBrand(asset.getMerchBrand());
                }
                if (asset.getMerchLimitedEdition() != null) {
                    existingAsset.setMerchLimitedEdition(asset.getMerchLimitedEdition());
                }
                if (asset.getMerchCollectorsEdition() != null) {
                    existingAsset.setMerchCollectorsEdition(asset.getMerchCollectorsEdition());
                }
                if (asset.getMerchOutOfPrint() != null) {
                    existingAsset.setMerchOutOfPrint(asset.getMerchOutOfPrint());
                }
                if (asset.getMerchTourMerch() != null) {
                    existingAsset.setMerchTourMerch(asset.getMerchTourMerch());
                }
                if (asset.getMerchItemDescription() != null) {
                    existingAsset.setMerchItemDescription(asset.getMerchItemDescription());
                }

                return existingAsset;
            })
            .map(assetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, asset.getId().toString())
        );
    }

    /**
     * {@code GET  /assets} : get all the assets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assets in body.
     */
    @GetMapping("/assets")
    public List<Asset> getAllAssets() {
        log.debug("REST request to get all Assets");
        return assetRepository.findAll();
    }

    /**
     * {@code GET  /assets/:id} : get the "id" asset.
     *
     * @param id the id of the asset to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the asset, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        log.debug("REST request to get Asset : {}", id);
        Optional<Asset> asset = assetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(asset);
    }

    /**
     * {@code DELETE  /assets/:id} : delete the "id" asset.
     *
     * @param id the id of the asset to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        log.debug("REST request to delete Asset : {}", id);
        assetRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
