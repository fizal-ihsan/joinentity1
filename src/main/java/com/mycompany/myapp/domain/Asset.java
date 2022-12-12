package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.AssetType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Asset.
 */
@Entity
@Table(name = "asset")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AssetType type;

    @Column(name = "isrc")
    private String isrc;

    @Column(name = "recording_duration")
    private String recordingDuration;

    @Column(name = "recording_cyear_cline")
    private String recordingCyearCline;

    @Column(name = "recording_pyear_pline")
    private String recordingPyearPline;

    @Column(name = "recording_main_genre")
    private String recordingMainGenre;

    @Column(name = "recording_main_genre_sub")
    private String recordingMainGenreSub;

    @Column(name = "recording_alternative_genre")
    private String recordingAlternativeGenre;

    @Column(name = "recording_alternative_genre_sub")
    private String recordingAlternativeGenreSub;

    @Column(name = "recording_master_owner")
    private String recordingMasterOwner;

    @Column(name = "recording_year_recording")
    private String recordingYearRecording;

    @Column(name = "recording_location")
    private String recordingLocation;

    @Column(name = "recording_label_country")
    private String recordingLabelCountry;

    @Column(name = "recording_bpm")
    private String recordingBpm;

    @Column(name = "recording_publishers")
    private String recordingPublishers;

    @Column(name = "recording_label_copy")
    private String recordingLabelCopy;

    @Column(name = "recording_additional_label_copy")
    private String recordingAdditionalLabelCopy;

    @Column(name = "recording_description")
    private String recordingDescription;

    @Column(name = "recording_tag_words")
    private String recordingTagWords;

    @Column(name = "recording_suggested_uses")
    private String recordingSuggestedUses;

    @Column(name = "musicalworks_type_of_work")
    private String musicalworksTypeOfWork;

    @Column(name = "musicalworks_version_type")
    private String musicalworksVersionType;

    @Column(name = "musicalworks_duration")
    private String musicalworksDuration;

    @Column(name = "musicalworks_language")
    private String musicalworksLanguage;

    @Column(name = "musicalworks_creation_date")
    private LocalDate musicalworksCreationDate;

    @Column(name = "musicalworks_publication_date")
    private LocalDate musicalworksPublicationDate;

    @Column(name = "musicalworks_registration_number")
    private String musicalworksRegistrationNumber;

    @Column(name = "musicalworks_registration_date")
    private LocalDate musicalworksRegistrationDate;

    @Column(name = "musicalworks_uncleared_sample")
    private Boolean musicalworksUnclearedSample;

    @Column(name = "musicalworks_controlled")
    private Boolean musicalworksControlled;

    @Column(name = "musicalworks_related_isrcs")
    private String musicalworksRelatedIsrcs;

    @Column(name = "musicalworks_notes")
    private String musicalworksNotes;

    @Column(name = "musicalworks_territory")
    private String musicalworksTerritory;

    @Column(name = "musicalworks_crc_publisher")
    private String musicalworksCrcPublisher;

    @Column(name = "musicalworks_administrator")
    private String musicalworksAdministrator;

    @Column(name = "musicalworks_sub_publisher")
    private String musicalworksSubPublisher;

    @Column(name = "musicalworks_income_collector")
    private String musicalworksIncomeCollector;

    @Column(name = "musicalworks_territories")
    private String musicalworksTerritories;

    @Column(name = "merch_item_type")
    private String merchItemType;

    @Column(name = "merch_part_number")
    private String merchPartNumber;

    @Column(name = "merch_season_year")
    private String merchSeasonYear;

    @Column(name = "merch_related_tour_artist")
    private String merchRelatedTourArtist;

    @Column(name = "merch_manufacturer_supplier")
    private String merchManufacturerSupplier;

    @Column(name = "merch_brand")
    private String merchBrand;

    @Column(name = "merch_limited_edition")
    private Boolean merchLimitedEdition;

    @Column(name = "merch_collectors_edition")
    private Boolean merchCollectorsEdition;

    @Column(name = "merch_out_of_print")
    private Boolean merchOutOfPrint;

    @Column(name = "merch_tour_merch")
    private Boolean merchTourMerch;

    @Column(name = "merch_item_description")
    private String merchItemDescription;

    @ManyToMany(mappedBy = "assets")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "assets" }, allowSetters = true)
    private Set<Release> releases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Asset id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Asset title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public Asset artist(String artist) {
        this.setArtist(artist);
        return this;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public AssetType getType() {
        return this.type;
    }

    public Asset type(AssetType type) {
        this.setType(type);
        return this;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public Asset isrc(String isrc) {
        this.setIsrc(isrc);
        return this;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getRecordingDuration() {
        return this.recordingDuration;
    }

    public Asset recordingDuration(String recordingDuration) {
        this.setRecordingDuration(recordingDuration);
        return this;
    }

    public void setRecordingDuration(String recordingDuration) {
        this.recordingDuration = recordingDuration;
    }

    public String getRecordingCyearCline() {
        return this.recordingCyearCline;
    }

    public Asset recordingCyearCline(String recordingCyearCline) {
        this.setRecordingCyearCline(recordingCyearCline);
        return this;
    }

    public void setRecordingCyearCline(String recordingCyearCline) {
        this.recordingCyearCline = recordingCyearCline;
    }

    public String getRecordingPyearPline() {
        return this.recordingPyearPline;
    }

    public Asset recordingPyearPline(String recordingPyearPline) {
        this.setRecordingPyearPline(recordingPyearPline);
        return this;
    }

    public void setRecordingPyearPline(String recordingPyearPline) {
        this.recordingPyearPline = recordingPyearPline;
    }

    public String getRecordingMainGenre() {
        return this.recordingMainGenre;
    }

    public Asset recordingMainGenre(String recordingMainGenre) {
        this.setRecordingMainGenre(recordingMainGenre);
        return this;
    }

    public void setRecordingMainGenre(String recordingMainGenre) {
        this.recordingMainGenre = recordingMainGenre;
    }

    public String getRecordingMainGenreSub() {
        return this.recordingMainGenreSub;
    }

    public Asset recordingMainGenreSub(String recordingMainGenreSub) {
        this.setRecordingMainGenreSub(recordingMainGenreSub);
        return this;
    }

    public void setRecordingMainGenreSub(String recordingMainGenreSub) {
        this.recordingMainGenreSub = recordingMainGenreSub;
    }

    public String getRecordingAlternativeGenre() {
        return this.recordingAlternativeGenre;
    }

    public Asset recordingAlternativeGenre(String recordingAlternativeGenre) {
        this.setRecordingAlternativeGenre(recordingAlternativeGenre);
        return this;
    }

    public void setRecordingAlternativeGenre(String recordingAlternativeGenre) {
        this.recordingAlternativeGenre = recordingAlternativeGenre;
    }

    public String getRecordingAlternativeGenreSub() {
        return this.recordingAlternativeGenreSub;
    }

    public Asset recordingAlternativeGenreSub(String recordingAlternativeGenreSub) {
        this.setRecordingAlternativeGenreSub(recordingAlternativeGenreSub);
        return this;
    }

    public void setRecordingAlternativeGenreSub(String recordingAlternativeGenreSub) {
        this.recordingAlternativeGenreSub = recordingAlternativeGenreSub;
    }

    public String getRecordingMasterOwner() {
        return this.recordingMasterOwner;
    }

    public Asset recordingMasterOwner(String recordingMasterOwner) {
        this.setRecordingMasterOwner(recordingMasterOwner);
        return this;
    }

    public void setRecordingMasterOwner(String recordingMasterOwner) {
        this.recordingMasterOwner = recordingMasterOwner;
    }

    public String getRecordingYearRecording() {
        return this.recordingYearRecording;
    }

    public Asset recordingYearRecording(String recordingYearRecording) {
        this.setRecordingYearRecording(recordingYearRecording);
        return this;
    }

    public void setRecordingYearRecording(String recordingYearRecording) {
        this.recordingYearRecording = recordingYearRecording;
    }

    public String getRecordingLocation() {
        return this.recordingLocation;
    }

    public Asset recordingLocation(String recordingLocation) {
        this.setRecordingLocation(recordingLocation);
        return this;
    }

    public void setRecordingLocation(String recordingLocation) {
        this.recordingLocation = recordingLocation;
    }

    public String getRecordingLabelCountry() {
        return this.recordingLabelCountry;
    }

    public Asset recordingLabelCountry(String recordingLabelCountry) {
        this.setRecordingLabelCountry(recordingLabelCountry);
        return this;
    }

    public void setRecordingLabelCountry(String recordingLabelCountry) {
        this.recordingLabelCountry = recordingLabelCountry;
    }

    public String getRecordingBpm() {
        return this.recordingBpm;
    }

    public Asset recordingBpm(String recordingBpm) {
        this.setRecordingBpm(recordingBpm);
        return this;
    }

    public void setRecordingBpm(String recordingBpm) {
        this.recordingBpm = recordingBpm;
    }

    public String getRecordingPublishers() {
        return this.recordingPublishers;
    }

    public Asset recordingPublishers(String recordingPublishers) {
        this.setRecordingPublishers(recordingPublishers);
        return this;
    }

    public void setRecordingPublishers(String recordingPublishers) {
        this.recordingPublishers = recordingPublishers;
    }

    public String getRecordingLabelCopy() {
        return this.recordingLabelCopy;
    }

    public Asset recordingLabelCopy(String recordingLabelCopy) {
        this.setRecordingLabelCopy(recordingLabelCopy);
        return this;
    }

    public void setRecordingLabelCopy(String recordingLabelCopy) {
        this.recordingLabelCopy = recordingLabelCopy;
    }

    public String getRecordingAdditionalLabelCopy() {
        return this.recordingAdditionalLabelCopy;
    }

    public Asset recordingAdditionalLabelCopy(String recordingAdditionalLabelCopy) {
        this.setRecordingAdditionalLabelCopy(recordingAdditionalLabelCopy);
        return this;
    }

    public void setRecordingAdditionalLabelCopy(String recordingAdditionalLabelCopy) {
        this.recordingAdditionalLabelCopy = recordingAdditionalLabelCopy;
    }

    public String getRecordingDescription() {
        return this.recordingDescription;
    }

    public Asset recordingDescription(String recordingDescription) {
        this.setRecordingDescription(recordingDescription);
        return this;
    }

    public void setRecordingDescription(String recordingDescription) {
        this.recordingDescription = recordingDescription;
    }

    public String getRecordingTagWords() {
        return this.recordingTagWords;
    }

    public Asset recordingTagWords(String recordingTagWords) {
        this.setRecordingTagWords(recordingTagWords);
        return this;
    }

    public void setRecordingTagWords(String recordingTagWords) {
        this.recordingTagWords = recordingTagWords;
    }

    public String getRecordingSuggestedUses() {
        return this.recordingSuggestedUses;
    }

    public Asset recordingSuggestedUses(String recordingSuggestedUses) {
        this.setRecordingSuggestedUses(recordingSuggestedUses);
        return this;
    }

    public void setRecordingSuggestedUses(String recordingSuggestedUses) {
        this.recordingSuggestedUses = recordingSuggestedUses;
    }

    public String getMusicalworksTypeOfWork() {
        return this.musicalworksTypeOfWork;
    }

    public Asset musicalworksTypeOfWork(String musicalworksTypeOfWork) {
        this.setMusicalworksTypeOfWork(musicalworksTypeOfWork);
        return this;
    }

    public void setMusicalworksTypeOfWork(String musicalworksTypeOfWork) {
        this.musicalworksTypeOfWork = musicalworksTypeOfWork;
    }

    public String getMusicalworksVersionType() {
        return this.musicalworksVersionType;
    }

    public Asset musicalworksVersionType(String musicalworksVersionType) {
        this.setMusicalworksVersionType(musicalworksVersionType);
        return this;
    }

    public void setMusicalworksVersionType(String musicalworksVersionType) {
        this.musicalworksVersionType = musicalworksVersionType;
    }

    public String getMusicalworksDuration() {
        return this.musicalworksDuration;
    }

    public Asset musicalworksDuration(String musicalworksDuration) {
        this.setMusicalworksDuration(musicalworksDuration);
        return this;
    }

    public void setMusicalworksDuration(String musicalworksDuration) {
        this.musicalworksDuration = musicalworksDuration;
    }

    public String getMusicalworksLanguage() {
        return this.musicalworksLanguage;
    }

    public Asset musicalworksLanguage(String musicalworksLanguage) {
        this.setMusicalworksLanguage(musicalworksLanguage);
        return this;
    }

    public void setMusicalworksLanguage(String musicalworksLanguage) {
        this.musicalworksLanguage = musicalworksLanguage;
    }

    public LocalDate getMusicalworksCreationDate() {
        return this.musicalworksCreationDate;
    }

    public Asset musicalworksCreationDate(LocalDate musicalworksCreationDate) {
        this.setMusicalworksCreationDate(musicalworksCreationDate);
        return this;
    }

    public void setMusicalworksCreationDate(LocalDate musicalworksCreationDate) {
        this.musicalworksCreationDate = musicalworksCreationDate;
    }

    public LocalDate getMusicalworksPublicationDate() {
        return this.musicalworksPublicationDate;
    }

    public Asset musicalworksPublicationDate(LocalDate musicalworksPublicationDate) {
        this.setMusicalworksPublicationDate(musicalworksPublicationDate);
        return this;
    }

    public void setMusicalworksPublicationDate(LocalDate musicalworksPublicationDate) {
        this.musicalworksPublicationDate = musicalworksPublicationDate;
    }

    public String getMusicalworksRegistrationNumber() {
        return this.musicalworksRegistrationNumber;
    }

    public Asset musicalworksRegistrationNumber(String musicalworksRegistrationNumber) {
        this.setMusicalworksRegistrationNumber(musicalworksRegistrationNumber);
        return this;
    }

    public void setMusicalworksRegistrationNumber(String musicalworksRegistrationNumber) {
        this.musicalworksRegistrationNumber = musicalworksRegistrationNumber;
    }

    public LocalDate getMusicalworksRegistrationDate() {
        return this.musicalworksRegistrationDate;
    }

    public Asset musicalworksRegistrationDate(LocalDate musicalworksRegistrationDate) {
        this.setMusicalworksRegistrationDate(musicalworksRegistrationDate);
        return this;
    }

    public void setMusicalworksRegistrationDate(LocalDate musicalworksRegistrationDate) {
        this.musicalworksRegistrationDate = musicalworksRegistrationDate;
    }

    public Boolean getMusicalworksUnclearedSample() {
        return this.musicalworksUnclearedSample;
    }

    public Asset musicalworksUnclearedSample(Boolean musicalworksUnclearedSample) {
        this.setMusicalworksUnclearedSample(musicalworksUnclearedSample);
        return this;
    }

    public void setMusicalworksUnclearedSample(Boolean musicalworksUnclearedSample) {
        this.musicalworksUnclearedSample = musicalworksUnclearedSample;
    }

    public Boolean getMusicalworksControlled() {
        return this.musicalworksControlled;
    }

    public Asset musicalworksControlled(Boolean musicalworksControlled) {
        this.setMusicalworksControlled(musicalworksControlled);
        return this;
    }

    public void setMusicalworksControlled(Boolean musicalworksControlled) {
        this.musicalworksControlled = musicalworksControlled;
    }

    public String getMusicalworksRelatedIsrcs() {
        return this.musicalworksRelatedIsrcs;
    }

    public Asset musicalworksRelatedIsrcs(String musicalworksRelatedIsrcs) {
        this.setMusicalworksRelatedIsrcs(musicalworksRelatedIsrcs);
        return this;
    }

    public void setMusicalworksRelatedIsrcs(String musicalworksRelatedIsrcs) {
        this.musicalworksRelatedIsrcs = musicalworksRelatedIsrcs;
    }

    public String getMusicalworksNotes() {
        return this.musicalworksNotes;
    }

    public Asset musicalworksNotes(String musicalworksNotes) {
        this.setMusicalworksNotes(musicalworksNotes);
        return this;
    }

    public void setMusicalworksNotes(String musicalworksNotes) {
        this.musicalworksNotes = musicalworksNotes;
    }

    public String getMusicalworksTerritory() {
        return this.musicalworksTerritory;
    }

    public Asset musicalworksTerritory(String musicalworksTerritory) {
        this.setMusicalworksTerritory(musicalworksTerritory);
        return this;
    }

    public void setMusicalworksTerritory(String musicalworksTerritory) {
        this.musicalworksTerritory = musicalworksTerritory;
    }

    public String getMusicalworksCrcPublisher() {
        return this.musicalworksCrcPublisher;
    }

    public Asset musicalworksCrcPublisher(String musicalworksCrcPublisher) {
        this.setMusicalworksCrcPublisher(musicalworksCrcPublisher);
        return this;
    }

    public void setMusicalworksCrcPublisher(String musicalworksCrcPublisher) {
        this.musicalworksCrcPublisher = musicalworksCrcPublisher;
    }

    public String getMusicalworksAdministrator() {
        return this.musicalworksAdministrator;
    }

    public Asset musicalworksAdministrator(String musicalworksAdministrator) {
        this.setMusicalworksAdministrator(musicalworksAdministrator);
        return this;
    }

    public void setMusicalworksAdministrator(String musicalworksAdministrator) {
        this.musicalworksAdministrator = musicalworksAdministrator;
    }

    public String getMusicalworksSubPublisher() {
        return this.musicalworksSubPublisher;
    }

    public Asset musicalworksSubPublisher(String musicalworksSubPublisher) {
        this.setMusicalworksSubPublisher(musicalworksSubPublisher);
        return this;
    }

    public void setMusicalworksSubPublisher(String musicalworksSubPublisher) {
        this.musicalworksSubPublisher = musicalworksSubPublisher;
    }

    public String getMusicalworksIncomeCollector() {
        return this.musicalworksIncomeCollector;
    }

    public Asset musicalworksIncomeCollector(String musicalworksIncomeCollector) {
        this.setMusicalworksIncomeCollector(musicalworksIncomeCollector);
        return this;
    }

    public void setMusicalworksIncomeCollector(String musicalworksIncomeCollector) {
        this.musicalworksIncomeCollector = musicalworksIncomeCollector;
    }

    public String getMusicalworksTerritories() {
        return this.musicalworksTerritories;
    }

    public Asset musicalworksTerritories(String musicalworksTerritories) {
        this.setMusicalworksTerritories(musicalworksTerritories);
        return this;
    }

    public void setMusicalworksTerritories(String musicalworksTerritories) {
        this.musicalworksTerritories = musicalworksTerritories;
    }

    public String getMerchItemType() {
        return this.merchItemType;
    }

    public Asset merchItemType(String merchItemType) {
        this.setMerchItemType(merchItemType);
        return this;
    }

    public void setMerchItemType(String merchItemType) {
        this.merchItemType = merchItemType;
    }

    public String getMerchPartNumber() {
        return this.merchPartNumber;
    }

    public Asset merchPartNumber(String merchPartNumber) {
        this.setMerchPartNumber(merchPartNumber);
        return this;
    }

    public void setMerchPartNumber(String merchPartNumber) {
        this.merchPartNumber = merchPartNumber;
    }

    public String getMerchSeasonYear() {
        return this.merchSeasonYear;
    }

    public Asset merchSeasonYear(String merchSeasonYear) {
        this.setMerchSeasonYear(merchSeasonYear);
        return this;
    }

    public void setMerchSeasonYear(String merchSeasonYear) {
        this.merchSeasonYear = merchSeasonYear;
    }

    public String getMerchRelatedTourArtist() {
        return this.merchRelatedTourArtist;
    }

    public Asset merchRelatedTourArtist(String merchRelatedTourArtist) {
        this.setMerchRelatedTourArtist(merchRelatedTourArtist);
        return this;
    }

    public void setMerchRelatedTourArtist(String merchRelatedTourArtist) {
        this.merchRelatedTourArtist = merchRelatedTourArtist;
    }

    public String getMerchManufacturerSupplier() {
        return this.merchManufacturerSupplier;
    }

    public Asset merchManufacturerSupplier(String merchManufacturerSupplier) {
        this.setMerchManufacturerSupplier(merchManufacturerSupplier);
        return this;
    }

    public void setMerchManufacturerSupplier(String merchManufacturerSupplier) {
        this.merchManufacturerSupplier = merchManufacturerSupplier;
    }

    public String getMerchBrand() {
        return this.merchBrand;
    }

    public Asset merchBrand(String merchBrand) {
        this.setMerchBrand(merchBrand);
        return this;
    }

    public void setMerchBrand(String merchBrand) {
        this.merchBrand = merchBrand;
    }

    public Boolean getMerchLimitedEdition() {
        return this.merchLimitedEdition;
    }

    public Asset merchLimitedEdition(Boolean merchLimitedEdition) {
        this.setMerchLimitedEdition(merchLimitedEdition);
        return this;
    }

    public void setMerchLimitedEdition(Boolean merchLimitedEdition) {
        this.merchLimitedEdition = merchLimitedEdition;
    }

    public Boolean getMerchCollectorsEdition() {
        return this.merchCollectorsEdition;
    }

    public Asset merchCollectorsEdition(Boolean merchCollectorsEdition) {
        this.setMerchCollectorsEdition(merchCollectorsEdition);
        return this;
    }

    public void setMerchCollectorsEdition(Boolean merchCollectorsEdition) {
        this.merchCollectorsEdition = merchCollectorsEdition;
    }

    public Boolean getMerchOutOfPrint() {
        return this.merchOutOfPrint;
    }

    public Asset merchOutOfPrint(Boolean merchOutOfPrint) {
        this.setMerchOutOfPrint(merchOutOfPrint);
        return this;
    }

    public void setMerchOutOfPrint(Boolean merchOutOfPrint) {
        this.merchOutOfPrint = merchOutOfPrint;
    }

    public Boolean getMerchTourMerch() {
        return this.merchTourMerch;
    }

    public Asset merchTourMerch(Boolean merchTourMerch) {
        this.setMerchTourMerch(merchTourMerch);
        return this;
    }

    public void setMerchTourMerch(Boolean merchTourMerch) {
        this.merchTourMerch = merchTourMerch;
    }

    public String getMerchItemDescription() {
        return this.merchItemDescription;
    }

    public Asset merchItemDescription(String merchItemDescription) {
        this.setMerchItemDescription(merchItemDescription);
        return this;
    }

    public void setMerchItemDescription(String merchItemDescription) {
        this.merchItemDescription = merchItemDescription;
    }

    public Set<Release> getReleases() {
        return this.releases;
    }

    public void setReleases(Set<Release> releases) {
        if (this.releases != null) {
            this.releases.forEach(i -> i.removeAsset(this));
        }
        if (releases != null) {
            releases.forEach(i -> i.addAsset(this));
        }
        this.releases = releases;
    }

    public Asset releases(Set<Release> releases) {
        this.setReleases(releases);
        return this;
    }

    public Asset addRelease(Release release) {
        this.releases.add(release);
        release.getAssets().add(this);
        return this;
    }

    public Asset removeRelease(Release release) {
        this.releases.remove(release);
        release.getAssets().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        return id != null && id.equals(((Asset) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Asset{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", artist='" + getArtist() + "'" +
            ", type='" + getType() + "'" +
            ", isrc='" + getIsrc() + "'" +
            ", recordingDuration='" + getRecordingDuration() + "'" +
            ", recordingCyearCline='" + getRecordingCyearCline() + "'" +
            ", recordingPyearPline='" + getRecordingPyearPline() + "'" +
            ", recordingMainGenre='" + getRecordingMainGenre() + "'" +
            ", recordingMainGenreSub='" + getRecordingMainGenreSub() + "'" +
            ", recordingAlternativeGenre='" + getRecordingAlternativeGenre() + "'" +
            ", recordingAlternativeGenreSub='" + getRecordingAlternativeGenreSub() + "'" +
            ", recordingMasterOwner='" + getRecordingMasterOwner() + "'" +
            ", recordingYearRecording='" + getRecordingYearRecording() + "'" +
            ", recordingLocation='" + getRecordingLocation() + "'" +
            ", recordingLabelCountry='" + getRecordingLabelCountry() + "'" +
            ", recordingBpm='" + getRecordingBpm() + "'" +
            ", recordingPublishers='" + getRecordingPublishers() + "'" +
            ", recordingLabelCopy='" + getRecordingLabelCopy() + "'" +
            ", recordingAdditionalLabelCopy='" + getRecordingAdditionalLabelCopy() + "'" +
            ", recordingDescription='" + getRecordingDescription() + "'" +
            ", recordingTagWords='" + getRecordingTagWords() + "'" +
            ", recordingSuggestedUses='" + getRecordingSuggestedUses() + "'" +
            ", musicalworksTypeOfWork='" + getMusicalworksTypeOfWork() + "'" +
            ", musicalworksVersionType='" + getMusicalworksVersionType() + "'" +
            ", musicalworksDuration='" + getMusicalworksDuration() + "'" +
            ", musicalworksLanguage='" + getMusicalworksLanguage() + "'" +
            ", musicalworksCreationDate='" + getMusicalworksCreationDate() + "'" +
            ", musicalworksPublicationDate='" + getMusicalworksPublicationDate() + "'" +
            ", musicalworksRegistrationNumber='" + getMusicalworksRegistrationNumber() + "'" +
            ", musicalworksRegistrationDate='" + getMusicalworksRegistrationDate() + "'" +
            ", musicalworksUnclearedSample='" + getMusicalworksUnclearedSample() + "'" +
            ", musicalworksControlled='" + getMusicalworksControlled() + "'" +
            ", musicalworksRelatedIsrcs='" + getMusicalworksRelatedIsrcs() + "'" +
            ", musicalworksNotes='" + getMusicalworksNotes() + "'" +
            ", musicalworksTerritory='" + getMusicalworksTerritory() + "'" +
            ", musicalworksCrcPublisher='" + getMusicalworksCrcPublisher() + "'" +
            ", musicalworksAdministrator='" + getMusicalworksAdministrator() + "'" +
            ", musicalworksSubPublisher='" + getMusicalworksSubPublisher() + "'" +
            ", musicalworksIncomeCollector='" + getMusicalworksIncomeCollector() + "'" +
            ", musicalworksTerritories='" + getMusicalworksTerritories() + "'" +
            ", merchItemType='" + getMerchItemType() + "'" +
            ", merchPartNumber='" + getMerchPartNumber() + "'" +
            ", merchSeasonYear='" + getMerchSeasonYear() + "'" +
            ", merchRelatedTourArtist='" + getMerchRelatedTourArtist() + "'" +
            ", merchManufacturerSupplier='" + getMerchManufacturerSupplier() + "'" +
            ", merchBrand='" + getMerchBrand() + "'" +
            ", merchLimitedEdition='" + getMerchLimitedEdition() + "'" +
            ", merchCollectorsEdition='" + getMerchCollectorsEdition() + "'" +
            ", merchOutOfPrint='" + getMerchOutOfPrint() + "'" +
            ", merchTourMerch='" + getMerchTourMerch() + "'" +
            ", merchItemDescription='" + getMerchItemDescription() + "'" +
            "}";
    }
}
