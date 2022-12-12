import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAsset } from 'app/shared/model/asset.model';
import { getEntities } from './asset.reducer';

export const Asset = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const assetList = useAppSelector(state => state.asset.entities);
  const loading = useAppSelector(state => state.asset.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="asset-heading" data-cy="AssetHeading">
        <Translate contentKey="joinentityApp.asset.home.title">Assets</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="joinentityApp.asset.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/asset/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="joinentityApp.asset.home.createLabel">Create new Asset</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {assetList && assetList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="joinentityApp.asset.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.title">Title</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.artist">Artist</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.isrc">Isrc</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingDuration">Recording Duration</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingCyearCline">Recording Cyear Cline</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingPyearPline">Recording Pyear Pline</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingMainGenre">Recording Main Genre</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingMainGenreSub">Recording Main Genre Sub</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingAlternativeGenre">Recording Alternative Genre</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingAlternativeGenreSub">Recording Alternative Genre Sub</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingMasterOwner">Recording Master Owner</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingYearRecording">Recording Year Recording</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingLocation">Recording Location</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingLabelCountry">Recording Label Country</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingBpm">Recording Bpm</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingPublishers">Recording Publishers</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingLabelCopy">Recording Label Copy</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingAdditionalLabelCopy">Recording Additional Label Copy</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingDescription">Recording Description</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingTagWords">Recording Tag Words</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.recordingSuggestedUses">Recording Suggested Uses</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksTypeOfWork">Musicalworks Type Of Work</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksVersionType">Musicalworks Version Type</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksDuration">Musicalworks Duration</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksLanguage">Musicalworks Language</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksCreationDate">Musicalworks Creation Date</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksPublicationDate">Musicalworks Publication Date</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksRegistrationNumber">Musicalworks Registration Number</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksRegistrationDate">Musicalworks Registration Date</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksUnclearedSample">Musicalworks Uncleared Sample</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksControlled">Musicalworks Controlled</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksRelatedIsrcs">Musicalworks Related Isrcs</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksNotes">Musicalworks Notes</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksTerritory">Musicalworks Territory</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksCrcPublisher">Musicalworks Crc Publisher</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksAdministrator">Musicalworks Administrator</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksSubPublisher">Musicalworks Sub Publisher</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksIncomeCollector">Musicalworks Income Collector</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.musicalworksTerritories">Musicalworks Territories</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchItemType">Merch Item Type</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchPartNumber">Merch Part Number</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchSeasonYear">Merch Season Year</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchRelatedTourArtist">Merch Related Tour Artist</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchManufacturerSupplier">Merch Manufacturer Supplier</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchBrand">Merch Brand</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchLimitedEdition">Merch Limited Edition</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchCollectorsEdition">Merch Collectors Edition</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchOutOfPrint">Merch Out Of Print</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchTourMerch">Merch Tour Merch</Translate>
                </th>
                <th>
                  <Translate contentKey="joinentityApp.asset.merchItemDescription">Merch Item Description</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {assetList.map((asset, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/asset/${asset.id}`} color="link" size="sm">
                      {asset.id}
                    </Button>
                  </td>
                  <td>{asset.title}</td>
                  <td>{asset.artist}</td>
                  <td>
                    <Translate contentKey={`joinentityApp.AssetType.${asset.type}`} />
                  </td>
                  <td>{asset.isrc}</td>
                  <td>{asset.recordingDuration}</td>
                  <td>{asset.recordingCyearCline}</td>
                  <td>{asset.recordingPyearPline}</td>
                  <td>{asset.recordingMainGenre}</td>
                  <td>{asset.recordingMainGenreSub}</td>
                  <td>{asset.recordingAlternativeGenre}</td>
                  <td>{asset.recordingAlternativeGenreSub}</td>
                  <td>{asset.recordingMasterOwner}</td>
                  <td>{asset.recordingYearRecording}</td>
                  <td>{asset.recordingLocation}</td>
                  <td>{asset.recordingLabelCountry}</td>
                  <td>{asset.recordingBpm}</td>
                  <td>{asset.recordingPublishers}</td>
                  <td>{asset.recordingLabelCopy}</td>
                  <td>{asset.recordingAdditionalLabelCopy}</td>
                  <td>{asset.recordingDescription}</td>
                  <td>{asset.recordingTagWords}</td>
                  <td>{asset.recordingSuggestedUses}</td>
                  <td>{asset.musicalworksTypeOfWork}</td>
                  <td>{asset.musicalworksVersionType}</td>
                  <td>{asset.musicalworksDuration}</td>
                  <td>{asset.musicalworksLanguage}</td>
                  <td>
                    {asset.musicalworksCreationDate ? (
                      <TextFormat type="date" value={asset.musicalworksCreationDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {asset.musicalworksPublicationDate ? (
                      <TextFormat type="date" value={asset.musicalworksPublicationDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{asset.musicalworksRegistrationNumber}</td>
                  <td>
                    {asset.musicalworksRegistrationDate ? (
                      <TextFormat type="date" value={asset.musicalworksRegistrationDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{asset.musicalworksUnclearedSample ? 'true' : 'false'}</td>
                  <td>{asset.musicalworksControlled ? 'true' : 'false'}</td>
                  <td>{asset.musicalworksRelatedIsrcs}</td>
                  <td>{asset.musicalworksNotes}</td>
                  <td>{asset.musicalworksTerritory}</td>
                  <td>{asset.musicalworksCrcPublisher}</td>
                  <td>{asset.musicalworksAdministrator}</td>
                  <td>{asset.musicalworksSubPublisher}</td>
                  <td>{asset.musicalworksIncomeCollector}</td>
                  <td>{asset.musicalworksTerritories}</td>
                  <td>{asset.merchItemType}</td>
                  <td>{asset.merchPartNumber}</td>
                  <td>{asset.merchSeasonYear}</td>
                  <td>{asset.merchRelatedTourArtist}</td>
                  <td>{asset.merchManufacturerSupplier}</td>
                  <td>{asset.merchBrand}</td>
                  <td>{asset.merchLimitedEdition ? 'true' : 'false'}</td>
                  <td>{asset.merchCollectorsEdition ? 'true' : 'false'}</td>
                  <td>{asset.merchOutOfPrint ? 'true' : 'false'}</td>
                  <td>{asset.merchTourMerch ? 'true' : 'false'}</td>
                  <td>{asset.merchItemDescription}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/asset/${asset.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/asset/${asset.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/asset/${asset.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="joinentityApp.asset.home.notFound">No Assets found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Asset;
