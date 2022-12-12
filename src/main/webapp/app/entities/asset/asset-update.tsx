import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRelease } from 'app/shared/model/release.model';
import { getEntities as getReleases } from 'app/entities/release/release.reducer';
import { IAsset } from 'app/shared/model/asset.model';
import { AssetType } from 'app/shared/model/enumerations/asset-type.model';
import { getEntity, updateEntity, createEntity, reset } from './asset.reducer';

export const AssetUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const releases = useAppSelector(state => state.release.entities);
  const assetEntity = useAppSelector(state => state.asset.entity);
  const loading = useAppSelector(state => state.asset.loading);
  const updating = useAppSelector(state => state.asset.updating);
  const updateSuccess = useAppSelector(state => state.asset.updateSuccess);
  const assetTypeValues = Object.keys(AssetType);

  const handleClose = () => {
    navigate('/asset');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getReleases({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...assetEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          type: 'RECORDING',
          ...assetEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="joinentityApp.asset.home.createOrEditLabel" data-cy="AssetCreateUpdateHeading">
            <Translate contentKey="joinentityApp.asset.home.createOrEditLabel">Create or edit a Asset</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="asset-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('joinentityApp.asset.title')} id="asset-title" name="title" data-cy="title" type="text" />
              <ValidatedField
                label={translate('joinentityApp.asset.artist')}
                id="asset-artist"
                name="artist"
                data-cy="artist"
                type="text"
              />
              <ValidatedField label={translate('joinentityApp.asset.type')} id="asset-type" name="type" data-cy="type" type="select">
                {assetTypeValues.map(assetType => (
                  <option value={assetType} key={assetType}>
                    {translate('joinentityApp.AssetType.' + assetType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label={translate('joinentityApp.asset.isrc')} id="asset-isrc" name="isrc" data-cy="isrc" type="text" />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingDuration')}
                id="asset-recordingDuration"
                name="recordingDuration"
                data-cy="recordingDuration"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingCyearCline')}
                id="asset-recordingCyearCline"
                name="recordingCyearCline"
                data-cy="recordingCyearCline"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingPyearPline')}
                id="asset-recordingPyearPline"
                name="recordingPyearPline"
                data-cy="recordingPyearPline"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingMainGenre')}
                id="asset-recordingMainGenre"
                name="recordingMainGenre"
                data-cy="recordingMainGenre"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingMainGenreSub')}
                id="asset-recordingMainGenreSub"
                name="recordingMainGenreSub"
                data-cy="recordingMainGenreSub"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingAlternativeGenre')}
                id="asset-recordingAlternativeGenre"
                name="recordingAlternativeGenre"
                data-cy="recordingAlternativeGenre"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingAlternativeGenreSub')}
                id="asset-recordingAlternativeGenreSub"
                name="recordingAlternativeGenreSub"
                data-cy="recordingAlternativeGenreSub"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingMasterOwner')}
                id="asset-recordingMasterOwner"
                name="recordingMasterOwner"
                data-cy="recordingMasterOwner"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingYearRecording')}
                id="asset-recordingYearRecording"
                name="recordingYearRecording"
                data-cy="recordingYearRecording"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingLocation')}
                id="asset-recordingLocation"
                name="recordingLocation"
                data-cy="recordingLocation"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingLabelCountry')}
                id="asset-recordingLabelCountry"
                name="recordingLabelCountry"
                data-cy="recordingLabelCountry"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingBpm')}
                id="asset-recordingBpm"
                name="recordingBpm"
                data-cy="recordingBpm"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingPublishers')}
                id="asset-recordingPublishers"
                name="recordingPublishers"
                data-cy="recordingPublishers"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingLabelCopy')}
                id="asset-recordingLabelCopy"
                name="recordingLabelCopy"
                data-cy="recordingLabelCopy"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingAdditionalLabelCopy')}
                id="asset-recordingAdditionalLabelCopy"
                name="recordingAdditionalLabelCopy"
                data-cy="recordingAdditionalLabelCopy"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingDescription')}
                id="asset-recordingDescription"
                name="recordingDescription"
                data-cy="recordingDescription"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingTagWords')}
                id="asset-recordingTagWords"
                name="recordingTagWords"
                data-cy="recordingTagWords"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.recordingSuggestedUses')}
                id="asset-recordingSuggestedUses"
                name="recordingSuggestedUses"
                data-cy="recordingSuggestedUses"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksTypeOfWork')}
                id="asset-musicalworksTypeOfWork"
                name="musicalworksTypeOfWork"
                data-cy="musicalworksTypeOfWork"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksVersionType')}
                id="asset-musicalworksVersionType"
                name="musicalworksVersionType"
                data-cy="musicalworksVersionType"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksDuration')}
                id="asset-musicalworksDuration"
                name="musicalworksDuration"
                data-cy="musicalworksDuration"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksLanguage')}
                id="asset-musicalworksLanguage"
                name="musicalworksLanguage"
                data-cy="musicalworksLanguage"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksCreationDate')}
                id="asset-musicalworksCreationDate"
                name="musicalworksCreationDate"
                data-cy="musicalworksCreationDate"
                type="date"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksPublicationDate')}
                id="asset-musicalworksPublicationDate"
                name="musicalworksPublicationDate"
                data-cy="musicalworksPublicationDate"
                type="date"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksRegistrationNumber')}
                id="asset-musicalworksRegistrationNumber"
                name="musicalworksRegistrationNumber"
                data-cy="musicalworksRegistrationNumber"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksRegistrationDate')}
                id="asset-musicalworksRegistrationDate"
                name="musicalworksRegistrationDate"
                data-cy="musicalworksRegistrationDate"
                type="date"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksUnclearedSample')}
                id="asset-musicalworksUnclearedSample"
                name="musicalworksUnclearedSample"
                data-cy="musicalworksUnclearedSample"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksControlled')}
                id="asset-musicalworksControlled"
                name="musicalworksControlled"
                data-cy="musicalworksControlled"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksRelatedIsrcs')}
                id="asset-musicalworksRelatedIsrcs"
                name="musicalworksRelatedIsrcs"
                data-cy="musicalworksRelatedIsrcs"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksNotes')}
                id="asset-musicalworksNotes"
                name="musicalworksNotes"
                data-cy="musicalworksNotes"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksTerritory')}
                id="asset-musicalworksTerritory"
                name="musicalworksTerritory"
                data-cy="musicalworksTerritory"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksCrcPublisher')}
                id="asset-musicalworksCrcPublisher"
                name="musicalworksCrcPublisher"
                data-cy="musicalworksCrcPublisher"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksAdministrator')}
                id="asset-musicalworksAdministrator"
                name="musicalworksAdministrator"
                data-cy="musicalworksAdministrator"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksSubPublisher')}
                id="asset-musicalworksSubPublisher"
                name="musicalworksSubPublisher"
                data-cy="musicalworksSubPublisher"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksIncomeCollector')}
                id="asset-musicalworksIncomeCollector"
                name="musicalworksIncomeCollector"
                data-cy="musicalworksIncomeCollector"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.musicalworksTerritories')}
                id="asset-musicalworksTerritories"
                name="musicalworksTerritories"
                data-cy="musicalworksTerritories"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchItemType')}
                id="asset-merchItemType"
                name="merchItemType"
                data-cy="merchItemType"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchPartNumber')}
                id="asset-merchPartNumber"
                name="merchPartNumber"
                data-cy="merchPartNumber"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchSeasonYear')}
                id="asset-merchSeasonYear"
                name="merchSeasonYear"
                data-cy="merchSeasonYear"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchRelatedTourArtist')}
                id="asset-merchRelatedTourArtist"
                name="merchRelatedTourArtist"
                data-cy="merchRelatedTourArtist"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchManufacturerSupplier')}
                id="asset-merchManufacturerSupplier"
                name="merchManufacturerSupplier"
                data-cy="merchManufacturerSupplier"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchBrand')}
                id="asset-merchBrand"
                name="merchBrand"
                data-cy="merchBrand"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchLimitedEdition')}
                id="asset-merchLimitedEdition"
                name="merchLimitedEdition"
                data-cy="merchLimitedEdition"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchCollectorsEdition')}
                id="asset-merchCollectorsEdition"
                name="merchCollectorsEdition"
                data-cy="merchCollectorsEdition"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchOutOfPrint')}
                id="asset-merchOutOfPrint"
                name="merchOutOfPrint"
                data-cy="merchOutOfPrint"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchTourMerch')}
                id="asset-merchTourMerch"
                name="merchTourMerch"
                data-cy="merchTourMerch"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('joinentityApp.asset.merchItemDescription')}
                id="asset-merchItemDescription"
                name="merchItemDescription"
                data-cy="merchItemDescription"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/asset" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AssetUpdate;
