import dayjs from 'dayjs';
import { IRelease } from 'app/shared/model/release.model';
import { AssetType } from 'app/shared/model/enumerations/asset-type.model';

export interface IAsset {
  id?: number;
  title?: string | null;
  artist?: string | null;
  type?: AssetType | null;
  isrc?: string | null;
  recordingDuration?: string | null;
  recordingCyearCline?: string | null;
  recordingPyearPline?: string | null;
  recordingMainGenre?: string | null;
  recordingMainGenreSub?: string | null;
  recordingAlternativeGenre?: string | null;
  recordingAlternativeGenreSub?: string | null;
  recordingMasterOwner?: string | null;
  recordingYearRecording?: string | null;
  recordingLocation?: string | null;
  recordingLabelCountry?: string | null;
  recordingBpm?: string | null;
  recordingPublishers?: string | null;
  recordingLabelCopy?: string | null;
  recordingAdditionalLabelCopy?: string | null;
  recordingDescription?: string | null;
  recordingTagWords?: string | null;
  recordingSuggestedUses?: string | null;
  musicalworksTypeOfWork?: string | null;
  musicalworksVersionType?: string | null;
  musicalworksDuration?: string | null;
  musicalworksLanguage?: string | null;
  musicalworksCreationDate?: string | null;
  musicalworksPublicationDate?: string | null;
  musicalworksRegistrationNumber?: string | null;
  musicalworksRegistrationDate?: string | null;
  musicalworksUnclearedSample?: boolean | null;
  musicalworksControlled?: boolean | null;
  musicalworksRelatedIsrcs?: string | null;
  musicalworksNotes?: string | null;
  musicalworksTerritory?: string | null;
  musicalworksCrcPublisher?: string | null;
  musicalworksAdministrator?: string | null;
  musicalworksSubPublisher?: string | null;
  musicalworksIncomeCollector?: string | null;
  musicalworksTerritories?: string | null;
  merchItemType?: string | null;
  merchPartNumber?: string | null;
  merchSeasonYear?: string | null;
  merchRelatedTourArtist?: string | null;
  merchManufacturerSupplier?: string | null;
  merchBrand?: string | null;
  merchLimitedEdition?: boolean | null;
  merchCollectorsEdition?: boolean | null;
  merchOutOfPrint?: boolean | null;
  merchTourMerch?: boolean | null;
  merchItemDescription?: string | null;
  releases?: IRelease[] | null;
}

export const defaultValue: Readonly<IAsset> = {
  musicalworksUnclearedSample: false,
  musicalworksControlled: false,
  merchLimitedEdition: false,
  merchCollectorsEdition: false,
  merchOutOfPrint: false,
  merchTourMerch: false,
};
