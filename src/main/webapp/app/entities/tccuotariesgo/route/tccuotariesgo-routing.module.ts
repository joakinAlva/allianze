import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCUOTARIESGOComponent } from '../list/tccuotariesgo.component';
import { TCCUOTARIESGODetailComponent } from '../detail/tccuotariesgo-detail.component';
import { TCCUOTARIESGOUpdateComponent } from '../update/tccuotariesgo-update.component';
import { TCCUOTARIESGORoutingResolveService } from './tccuotariesgo-routing-resolve.service';

const tCCUOTARIESGORoute: Routes = [
  {
    path: '',
    component: TCCUOTARIESGOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaRiesgo/view',
    component: TCCUOTARIESGODetailComponent,
    resolve: {
      tCCUOTARIESGO: TCCUOTARIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCUOTARIESGOUpdateComponent,
    resolve: {
      tCCUOTARIESGO: TCCUOTARIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaRiesgo/edit',
    component: TCCUOTARIESGOUpdateComponent,
    resolve: {
      tCCUOTARIESGO: TCCUOTARIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCUOTARIESGORoute)],
  exports: [RouterModule],
})
export class TCCUOTARIESGORoutingModule {}
