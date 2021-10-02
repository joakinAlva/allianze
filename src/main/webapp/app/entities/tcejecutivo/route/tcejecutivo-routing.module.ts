import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCEJECUTIVOComponent } from '../list/tcejecutivo.component';
import { TCEJECUTIVODetailComponent } from '../detail/tcejecutivo-detail.component';
import { TCEJECUTIVOUpdateComponent } from '../update/tcejecutivo-update.component';
import { TCEJECUTIVORoutingResolveService } from './tcejecutivo-routing-resolve.service';

const tCEJECUTIVORoute: Routes = [
  {
    path: '',
    component: TCEJECUTIVOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEjecutivo/view',
    component: TCEJECUTIVODetailComponent,
    resolve: {
      tCEJECUTIVO: TCEJECUTIVORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCEJECUTIVOUpdateComponent,
    resolve: {
      tCEJECUTIVO: TCEJECUTIVORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEjecutivo/edit',
    component: TCEJECUTIVOUpdateComponent,
    resolve: {
      tCEJECUTIVO: TCEJECUTIVORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCEJECUTIVORoute)],
  exports: [RouterModule],
})
export class TCEJECUTIVORoutingModule {}
