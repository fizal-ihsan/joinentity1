import dayjs from 'dayjs';
import { IAsset } from 'app/shared/model/asset.model';
import { ReleaseStatus } from 'app/shared/model/enumerations/release-status.model';
import { ReleaseType } from 'app/shared/model/enumerations/release-type.model';
import { ReleaseMediaType } from 'app/shared/model/enumerations/release-media-type.model';
import { FormatType } from 'app/shared/model/enumerations/format-type.model';

export interface IRelease {
  id?: number;
  catalogNumber?: string | null;
  releaseDate?: string | null;
  title?: string | null;
  version?: string | null;
  artist?: string | null;
  label?: string | null;
  status?: ReleaseStatus | null;
  type?: ReleaseType | null;
  mediaType?: ReleaseMediaType | null;
  format?: FormatType | null;
  assets?: IAsset[] | null;
}

export const defaultValue: Readonly<IRelease> = {};
