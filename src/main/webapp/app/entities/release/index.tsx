import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Release from './release';
import ReleaseDetail from './release-detail';
import ReleaseUpdate from './release-update';
import ReleaseDeleteDialog from './release-delete-dialog';

const ReleaseRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Release />} />
    <Route path="new" element={<ReleaseUpdate />} />
    <Route path=":id">
      <Route index element={<ReleaseDetail />} />
      <Route path="edit" element={<ReleaseUpdate />} />
      <Route path="delete" element={<ReleaseDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ReleaseRoutes;
