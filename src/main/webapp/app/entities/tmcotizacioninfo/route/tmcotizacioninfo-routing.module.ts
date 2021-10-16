import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TMCOTIZACIONINFOComponent } from '../list/tmcotizacioninfo.component';
import { TMCOTIZACIONINFODetailComponent } from '../detail/tmcotizacioninfo-detail.component';
import { TMCOTIZACIONINFOUpdateComponent } from '../update/tmcotizacioninfo-update.component';
import { TMCOTIZACIONINFORoutingResolveService } from './tmcotizacioninfo-routing-resolve.service';

const tMCOTIZACIONINFORoute: Routes = [
  {
    path: '',
    component: TMCOTIZACIONINFOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacionInfo/view',
    component: TMCOTIZACIONINFODetailComponent,
    resolve: {
      tMCOTIZACIONINFO: TMCOTIZACIONINFORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TMCOTIZACIONINFOUpdateComponent,
    resolve: {
      tMCOTIZACIONINFO: TMCOTIZACIONINFORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacionInfo/edit',
    component: TMCOTIZACIONINFOUpdateComponent,
    resolve: {
      tMCOTIZACIONINFO: TMCOTIZACIONINFORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tMCOTIZACIONINFORoute)],
  exports: [RouterModule],
})
export class TMCOTIZACIONINFORoutingModule {}
