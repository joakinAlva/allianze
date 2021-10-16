import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCESTATUSCOTIZACIONComponent } from '../list/tcestatuscotizacion.component';
import { TCESTATUSCOTIZACIONDetailComponent } from '../detail/tcestatuscotizacion-detail.component';
import { TCESTATUSCOTIZACIONUpdateComponent } from '../update/tcestatuscotizacion-update.component';
import { TCESTATUSCOTIZACIONRoutingResolveService } from './tcestatuscotizacion-routing-resolve.service';

const tCESTATUSCOTIZACIONRoute: Routes = [
  {
    path: '',
    component: TCESTATUSCOTIZACIONComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEstatusCotizacion/view',
    component: TCESTATUSCOTIZACIONDetailComponent,
    resolve: {
      tCESTATUSCOTIZACION: TCESTATUSCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCESTATUSCOTIZACIONUpdateComponent,
    resolve: {
      tCESTATUSCOTIZACION: TCESTATUSCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEstatusCotizacion/edit',
    component: TCESTATUSCOTIZACIONUpdateComponent,
    resolve: {
      tCESTATUSCOTIZACION: TCESTATUSCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCESTATUSCOTIZACIONRoute)],
  exports: [RouterModule],
})
export class TCESTATUSCOTIZACIONRoutingModule {}
