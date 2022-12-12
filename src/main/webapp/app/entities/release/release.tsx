import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRelease } from 'app/shared/model/release.model';
import { getEntities } from './release.reducer';

export const Release = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const releaseList = useAppSelector(state => state.release.entities);
  const loading = useAppSelector(state => state.release.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="release-heading" data-cy="ReleaseHeading">
        <Translate contentKey="joinentityApp.release.home.title">Releases</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="joinentityApp.release.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/release/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="joinentityApp.release.home.createLabel">Create new Release</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {releaseList && releaseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="joinentityApp.release.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.catalogNumber">Catalog Number</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.releaseDate">Release Date</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.title">Title</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.version">Version</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.artist">Artist</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.label">Label</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.mediaType">Media Type</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.format">Format</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.release.asset">Asset</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {releaseList.map((release, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/release/${release.id}`} color="link" size="sm">
                      {release.id}
                    </Button>
                  </td>
                  <td>{release.catalogNumber}</td>
                  <td>
                    {release.releaseDate ? <TextFormat type="date" value={release.releaseDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{release.title}</td>
                  <td>{release.version}</td>
                  <td>{release.artist}</td>
                  <td>{release.label}</td>
                  <td>
                    <Translate contentKey={`joinentityApp.ReleaseStatus.${release.status}`} />
                  </td>
                  <td>
                    <Translate contentKey={`joinentityApp.ReleaseType.${release.type}`} />
                  </td>
                  <td>
                    <Translate contentKey={`joinentityApp.ReleaseMediaType.${release.mediaType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`joinentityApp.FormatType.${release.format}`} />
                  </td>
                  <td>
                    {release.assets
                      ? release.assets.map((val, j) => (
                          <span key={j}>
                            <Link to={`/asset/${val.id}`}>{val.id}</Link>
                            {j === release.assets.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/release/${release.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/release/${release.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/release/${release.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="joinentityApp.release.home.notFound">No Releases found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Release;
