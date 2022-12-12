import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './asset.reducer';

export const AssetDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const assetEntity = useAppSelector(state => state.asset.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="assetDetailsHeading">
          <Translate contentKey="joinentityApp.asset.detail.title">Asset</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{assetEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="joinentityApp.asset.title">Title</Translate>
            </span>
          </dt>
          <dd>{assetEntity.title}</dd>
          <dt>
            <span id="artist">
              <Translate contentKey="joinentityApp.asset.artist">Artist</Translate>
            </span>
          </dt>
          <dd>{assetEntity.artist}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="joinentityApp.asset.type">Type</Translate>
            </span>
          </dt>
          <dd>{assetEntity.type}</dd>
          <dt>
            <span id="isrc">
              <Translate contentKey="joinentityApp.asset.isrc">Isrc</Translate>
            </span>
          </dt>
          <dd>{assetEntity.isrc}</dd>
          <dt>
            <span id="recordingDuration">
              <Translate contentKey="joinentityApp.asset.recordingDuration">Recording Duration</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingDuration}</dd>
          <dt>
            <span id="recordingCyearCline">
              <Translate contentKey="joinentityApp.asset.recordingCyearCline">Recording Cyear Cline</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingCyearCline}</dd>
          <dt>
            <span id="recordingPyearPline">
              <Translate contentKey="joinentityApp.asset.recordingPyearPline">Recording Pyear Pline</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingPyearPline}</dd>
          <dt>
            <span id="recordingMainGenre">
              <Translate contentKey="joinentityApp.asset.recordingMainGenre">Recording Main Genre</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingMainGenre}</dd>
          <dt>
            <span id="recordingMainGenreSub">
              <Translate contentKey="joinentityApp.asset.recordingMainGenreSub">Recording Main Genre Sub</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingMainGenreSub}</dd>
          <dt>
            <span id="recordingAlternativeGenre">
              <Translate contentKey="joinentityApp.asset.recordingAlternativeGenre">Recording Alternative Genre</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingAlternativeGenre}</dd>
          <dt>
            <span id="recordingAlternativeGenreSub">
              <Translate contentKey="joinentityApp.asset.recordingAlternativeGenreSub">Recording Alternative Genre Sub</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingAlternativeGenreSub}</dd>
          <dt>
            <span id="recordingMasterOwner">
              <Translate contentKey="joinentityApp.asset.recordingMasterOwner">Recording Master Owner</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingMasterOwner}</dd>
          <dt>
            <span id="recordingYearRecording">
              <Translate contentKey="joinentityApp.asset.recordingYearRecording">Recording Year Recording</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingYearRecording}</dd>
          <dt>
            <span id="recordingLocation">
              <Translate contentKey="joinentityApp.asset.recordingLocation">Recording Location</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingLocation}</dd>
          <dt>
            <span id="recordingLabelCountry">
              <Translate contentKey="joinentityApp.asset.recordingLabelCountry">Recording Label Country</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingLabelCountry}</dd>
          <dt>
            <span id="recordingBpm">
              <Translate contentKey="joinentityApp.asset.recordingBpm">Recording Bpm</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingBpm}</dd>
          <dt>
            <span id="recordingPublishers">
              <Translate contentKey="joinentityApp.asset.recordingPublishers">Recording Publishers</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingPublishers}</dd>
          <dt>
            <span id="recordingLabelCopy">
              <Translate contentKey="joinentityApp.asset.recordingLabelCopy">Recording Label Copy</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingLabelCopy}</dd>
          <dt>
            <span id="recordingAdditionalLabelCopy">
              <Translate contentKey="joinentityApp.asset.recordingAdditionalLabelCopy">Recording Additional Label Copy</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingAdditionalLabelCopy}</dd>
          <dt>
            <span id="recordingDescription">
              <Translate contentKey="joinentityApp.asset.recordingDescription">Recording Description</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingDescription}</dd>
          <dt>
            <span id="recordingTagWords">
              <Translate contentKey="joinentityApp.asset.recordingTagWords">Recording Tag Words</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingTagWords}</dd>
          <dt>
            <span id="recordingSuggestedUses">
              <Translate contentKey="joinentityApp.asset.recordingSuggestedUses">Recording Suggested Uses</Translate>
            </span>
          </dt>
          <dd>{assetEntity.recordingSuggestedUses}</dd>
          <dt>
            <span id="musicalworksTypeOfWork">
              <Translate contentKey="joinentityApp.asset.musicalworksTypeOfWork">Musicalworks Type Of Work</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksTypeOfWork}</dd>
          <dt>
            <span id="musicalworksVersionType">
              <Translate contentKey="joinentityApp.asset.musicalworksVersionType">Musicalworks Version Type</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksVersionType}</dd>
          <dt>
            <span id="musicalworksDuration">
              <Translate contentKey="joinentityApp.asset.musicalworksDuration">Musicalworks Duration</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksDuration}</dd>
          <dt>
            <span id="musicalworksLanguage">
              <Translate contentKey="joinentityApp.asset.musicalworksLanguage">Musicalworks Language</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksLanguage}</dd>
          <dt>
            <span id="musicalworksCreationDate">
              <Translate contentKey="joinentityApp.asset.musicalworksCreationDate">Musicalworks Creation Date</Translate>
            </span>
          </dt>
          <dd>
            {assetEntity.musicalworksCreationDate ? (
              <TextFormat value={assetEntity.musicalworksCreationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="musicalworksPublicationDate">
              <Translate contentKey="joinentityApp.asset.musicalworksPublicationDate">Musicalworks Publication Date</Translate>
            </span>
          </dt>
          <dd>
            {assetEntity.musicalworksPublicationDate ? (
              <TextFormat value={assetEntity.musicalworksPublicationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="musicalworksRegistrationNumber">
              <Translate contentKey="joinentityApp.asset.musicalworksRegistrationNumber">Musicalworks Registration Number</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksRegistrationNumber}</dd>
          <dt>
            <span id="musicalworksRegistrationDate">
              <Translate contentKey="joinentityApp.asset.musicalworksRegistrationDate">Musicalworks Registration Date</Translate>
            </span>
          </dt>
          <dd>
            {assetEntity.musicalworksRegistrationDate ? (
              <TextFormat value={assetEntity.musicalworksRegistrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="musicalworksUnclearedSample">
              <Translate contentKey="joinentityApp.asset.musicalworksUnclearedSample">Musicalworks Uncleared Sample</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksUnclearedSample ? 'true' : 'false'}</dd>
          <dt>
            <span id="musicalworksControlled">
              <Translate contentKey="joinentityApp.asset.musicalworksControlled">Musicalworks Controlled</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksControlled ? 'true' : 'false'}</dd>
          <dt>
            <span id="musicalworksRelatedIsrcs">
              <Translate contentKey="joinentityApp.asset.musicalworksRelatedIsrcs">Musicalworks Related Isrcs</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksRelatedIsrcs}</dd>
          <dt>
            <span id="musicalworksNotes">
              <Translate contentKey="joinentityApp.asset.musicalworksNotes">Musicalworks Notes</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksNotes}</dd>
          <dt>
            <span id="musicalworksTerritory">
              <Translate contentKey="joinentityApp.asset.musicalworksTerritory">Musicalworks Territory</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksTerritory}</dd>
          <dt>
            <span id="musicalworksCrcPublisher">
              <Translate contentKey="joinentityApp.asset.musicalworksCrcPublisher">Musicalworks Crc Publisher</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksCrcPublisher}</dd>
          <dt>
            <span id="musicalworksAdministrator">
              <Translate contentKey="joinentityApp.asset.musicalworksAdministrator">Musicalworks Administrator</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksAdministrator}</dd>
          <dt>
            <span id="musicalworksSubPublisher">
              <Translate contentKey="joinentityApp.asset.musicalworksSubPublisher">Musicalworks Sub Publisher</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksSubPublisher}</dd>
          <dt>
            <span id="musicalworksIncomeCollector">
              <Translate contentKey="joinentityApp.asset.musicalworksIncomeCollector">Musicalworks Income Collector</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksIncomeCollector}</dd>
          <dt>
            <span id="musicalworksTerritories">
              <Translate contentKey="joinentityApp.asset.musicalworksTerritories">Musicalworks Territories</Translate>
            </span>
          </dt>
          <dd>{assetEntity.musicalworksTerritories}</dd>
          <dt>
            <span id="merchItemType">
              <Translate contentKey="joinentityApp.asset.merchItemType">Merch Item Type</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchItemType}</dd>
          <dt>
            <span id="merchPartNumber">
              <Translate contentKey="joinentityApp.asset.merchPartNumber">Merch Part Number</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchPartNumber}</dd>
          <dt>
            <span id="merchSeasonYear">
              <Translate contentKey="joinentityApp.asset.merchSeasonYear">Merch Season Year</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchSeasonYear}</dd>
          <dt>
            <span id="merchRelatedTourArtist">
              <Translate contentKey="joinentityApp.asset.merchRelatedTourArtist">Merch Related Tour Artist</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchRelatedTourArtist}</dd>
          <dt>
            <span id="merchManufacturerSupplier">
              <Translate contentKey="joinentityApp.asset.merchManufacturerSupplier">Merch Manufacturer Supplier</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchManufacturerSupplier}</dd>
          <dt>
            <span id="merchBrand">
              <Translate contentKey="joinentityApp.asset.merchBrand">Merch Brand</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchBrand}</dd>
          <dt>
            <span id="merchLimitedEdition">
              <Translate contentKey="joinentityApp.asset.merchLimitedEdition">Merch Limited Edition</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchLimitedEdition ? 'true' : 'false'}</dd>
          <dt>
            <span id="merchCollectorsEdition">
              <Translate contentKey="joinentityApp.asset.merchCollectorsEdition">Merch Collectors Edition</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchCollectorsEdition ? 'true' : 'false'}</dd>
          <dt>
            <span id="merchOutOfPrint">
              <Translate contentKey="joinentityApp.asset.merchOutOfPrint">Merch Out Of Print</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchOutOfPrint ? 'true' : 'false'}</dd>
          <dt>
            <span id="merchTourMerch">
              <Translate contentKey="joinentityApp.asset.merchTourMerch">Merch Tour Merch</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchTourMerch ? 'true' : 'false'}</dd>
          <dt>
            <span id="merchItemDescription">
              <Translate contentKey="joinentityApp.asset.merchItemDescription">Merch Item Description</Translate>
            </span>
          </dt>
          <dd>{assetEntity.merchItemDescription}</dd>
        </dl>
        <Button tag={Link} to="/asset" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/asset/${assetEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AssetDetail;
