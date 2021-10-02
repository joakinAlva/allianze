import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCUOTATARIFASDESCComponent } from '../list/tccuotatarifasdesc.component';
import { TCCUOTATARIFASDESCDetailComponent } from '../detail/tccuotatarifasdesc-detail.component';
import { TCCUOTATARIFASDESCUpdateComponent } from '../update/tccuotatarifasdesc-update.component';
import { TCCUOTATARIFASDESCRoutingResolveService } from './tccuotatarifasdesc-routing-resolve.service';

const tCCUOTATARIFASDESCRoute: Routes = [
  {
    path: '',
    component: TCCUOTATARIFASDESCComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaTarifaSdesc/view',
    component: TCCUOTATARIFASDESCDetailComponent,
    resolve: {
      tCCUOTATARIFASDESC: TCCUOTATARIFASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCUOTATARIFASDESCUpdateComponent,
    resolve: {
      tCCUOTATARIFASDESC: TCCUOTATARIFASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaTarifaSdesc/edit',
    component: TCCUOTATARIFASDESCUpdateComponent,
    resolve: {
      tCCUOTATARIFASDESC: TCCUOTATARIFASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCUOTATARIFASDESCRoute)],
  exports: [RouterModule],
})
export class TCCUOTATARIFASDESCRoutingModule {}
