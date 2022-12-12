import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './release.reducer';

export const ReleaseDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const releaseEntity = useAppSelector(state => state.release.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="releaseDetailsHeading">
          <Translate contentKey="joinentityApp.release.detail.title">Release</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.id}</dd>
          <dt>
            <span id="catalogNumber">
              <Translate contentKey="joinentityApp.release.catalogNumber">Catalog Number</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.catalogNumber}</dd>
          <dt>
            <span id="releaseDate">
              <Translate contentKey="joinentityApp.release.releaseDate">Release Date</Translate>
            </span>
          </dt>
          <dd>
            {releaseEntity.releaseDate ? <TextFormat value={releaseEntity.releaseDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="title">
              <Translate contentKey="joinentityApp.release.title">Title</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.title}</dd>
          <dt>
            <span id="version">
              <Translate contentKey="joinentityApp.release.version">Version</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.version}</dd>
          <dt>
            <span id="artist">
              <Translate contentKey="joinentityApp.release.artist">Artist</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.artist}</dd>
          <dt>
            <span id="label">
              <Translate contentKey="joinentityApp.release.label">Label</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.label}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="joinentityApp.release.status">Status</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.status}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="joinentityApp.release.type">Type</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.type}</dd>
          <dt>
            <span id="mediaType">
              <Translate contentKey="joinentityApp.release.mediaType">Media Type</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.mediaType}</dd>
          <dt>
            <span id="format">
              <Translate contentKey="joinentityApp.release.format">Format</Translate>
            </span>
          </dt>
          <dd>{releaseEntity.format}</dd>
          <dt>
            <Translate contentKey="joinentityApp.release.asset">Asset</Translate>
          </dt>
          <dd>
            {releaseEntity.assets
              ? releaseEntity.assets.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {releaseEntity.assets && i === releaseEntity.assets.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/release" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/release/${releaseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ReleaseDetail;
