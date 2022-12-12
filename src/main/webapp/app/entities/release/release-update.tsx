import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAsset } from 'app/shared/model/asset.model';
import { getEntities as getAssets } from 'app/entities/asset/asset.reducer';
import { IRelease } from 'app/shared/model/release.model';
import { ReleaseStatus } from 'app/shared/model/enumerations/release-status.model';
import { ReleaseType } from 'app/shared/model/enumerations/release-type.model';
import { ReleaseMediaType } from 'app/shared/model/enumerations/release-media-type.model';
import { FormatType } from 'app/shared/model/enumerations/format-type.model';
import { getEntity, updateEntity, createEntity, reset } from './release.reducer';

export const ReleaseUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const assets = useAppSelector(state => state.asset.entities);
  const releaseEntity = useAppSelector(state => state.release.entity);
  const loading = useAppSelector(state => state.release.loading);
  const updating = useAppSelector(state => state.release.updating);
  const updateSuccess = useAppSelector(state => state.release.updateSuccess);
  const releaseStatusValues = Object.keys(ReleaseStatus);
  const releaseTypeValues = Object.keys(ReleaseType);
  const releaseMediaTypeValues = Object.keys(ReleaseMediaType);
  const formatTypeValues = Object.keys(FormatType);

  const handleClose = () => {
    navigate('/release');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAssets({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...releaseEntity,
      ...values,
      assets: mapIdList(values.assets),
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
          status: 'ACTIVE',
          type: 'TRACK',
          mediaType: 'CD',
          format: 'DIGITAL',
          ...releaseEntity,
          assets: releaseEntity?.assets?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="joinentityApp.release.home.createOrEditLabel" data-cy="ReleaseCreateUpdateHeading">
            <Translate contentKey="joinentityApp.release.home.createOrEditLabel">Create or edit a Release</Translate>
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
                  id="release-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('joinentityApp.release.catalogNumber')}
                id="release-catalogNumber"
                name="catalogNumber"
                data-cy="catalogNumber"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.release.releaseDate')}
                id="release-releaseDate"
                name="releaseDate"
                data-cy="releaseDate"
                type="date"
              />
              <ValidatedField
                label={translate('joinentityApp.release.title')}
                id="release-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.release.version')}
                id="release-version"
                name="version"
                data-cy="version"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.release.artist')}
                id="release-artist"
                name="artist"
                data-cy="artist"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.release.label')}
                id="release-label"
                name="label"
                data-cy="label"
                type="text"
              />
              <ValidatedField
                label={translate('joinentityApp.release.status')}
                id="release-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {releaseStatusValues.map(releaseStatus => (
                  <option value={releaseStatus} key={releaseStatus}>
                    {translate('joinentityApp.ReleaseStatus.' + releaseStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label={translate('joinentityApp.release.type')} id="release-type" name="type" data-cy="type" type="select">
                {releaseTypeValues.map(releaseType => (
                  <option value={releaseType} key={releaseType}>
                    {translate('joinentityApp.ReleaseType.' + releaseType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('joinentityApp.release.mediaType')}
                id="release-mediaType"
                name="mediaType"
                data-cy="mediaType"
                type="select"
              >
                {releaseMediaTypeValues.map(releaseMediaType => (
                  <option value={releaseMediaType} key={releaseMediaType}>
                    {translate('joinentityApp.ReleaseMediaType.' + releaseMediaType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('joinentityApp.release.format')}
                id="release-format"
                name="format"
                data-cy="format"
                type="select"
              >
                {formatTypeValues.map(formatType => (
                  <option value={formatType} key={formatType}>
                    {translate('joinentityApp.FormatType.' + formatType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('joinentityApp.release.asset')}
                id="release-asset"
                data-cy="asset"
                type="select"
                multiple
                name="assets"
              >
                <option value="" key="0" />
                {assets
                  ? assets.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/release" replace color="info">
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

export default ReleaseUpdate;
