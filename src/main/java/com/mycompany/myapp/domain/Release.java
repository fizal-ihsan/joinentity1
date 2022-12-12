package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.FormatType;
import com.mycompany.myapp.domain.enumeration.ReleaseMediaType;
import com.mycompany.myapp.domain.enumeration.ReleaseStatus;
import com.mycompany.myapp.domain.enumeration.ReleaseType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Release.
 */
@Entity
@Table(name = "release")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Release implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "catalog_number")
    private String catalogNumber;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "version")
    private String version;

    @Column(name = "artist")
    private String artist;

    @Column(name = "label")
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReleaseStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReleaseType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private ReleaseMediaType mediaType;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private FormatType format;

    @ManyToMany
    @JoinTable(
        name = "rel_release__asset",
        joinColumns = @JoinColumn(name = "release_id"),
        inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "releases" }, allowSetters = true)
    private Set<Asset> assets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Release id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatalogNumber() {
        return this.catalogNumber;
    }

    public Release catalogNumber(String catalogNumber) {
        this.setCatalogNumber(catalogNumber);
        return this;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public Release releaseDate(LocalDate releaseDate) {
        this.setReleaseDate(releaseDate);
        return this;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return this.title;
    }

    public Release title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return this.version;
    }

    public Release version(String version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArtist() {
        return this.artist;
    }

    public Release artist(String artist) {
        this.setArtist(artist);
        return this;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return this.label;
    }

    public Release label(String label) {
        this.setLabel(label);
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ReleaseStatus getStatus() {
        return this.status;
    }

    public Release status(ReleaseStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ReleaseStatus status) {
        this.status = status;
    }

    public ReleaseType getType() {
        return this.type;
    }

    public Release type(ReleaseType type) {
        this.setType(type);
        return this;
    }

    public void setType(ReleaseType type) {
        this.type = type;
    }

    public ReleaseMediaType getMediaType() {
        return this.mediaType;
    }

    public Release mediaType(ReleaseMediaType mediaType) {
        this.setMediaType(mediaType);
        return this;
    }

    public void setMediaType(ReleaseMediaType mediaType) {
        this.mediaType = mediaType;
    }

    public FormatType getFormat() {
        return this.format;
    }

    public Release format(FormatType format) {
        this.setFormat(format);
        return this;
    }

    public void setFormat(FormatType format) {
        this.format = format;
    }

    public Set<Asset> getAssets() {
        return this.assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public Release assets(Set<Asset> assets) {
        this.setAssets(assets);
        return this;
    }

    public Release addAsset(Asset asset) {
        this.assets.add(asset);
        asset.getReleases().add(this);
        return this;
    }

    public Release removeAsset(Asset asset) {
        this.assets.remove(asset);
        asset.getReleases().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Release)) {
            return false;
        }
        return id != null && id.equals(((Release) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Release{" +
            "id=" + getId() +
            ", catalogNumber='" + getCatalogNumber() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", title='" + getTitle() + "'" +
            ", version='" + getVersion() + "'" +
            ", artist='" + getArtist() + "'" +
            ", label='" + getLabel() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", mediaType='" + getMediaType() + "'" +
            ", format='" + getFormat() + "'" +
            "}";
    }
}
