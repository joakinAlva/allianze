import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TMCOTIZACIONEXPPROPIAComponent } from '../list/tmcotizacionexppropia.component';
import { TMCOTIZACIONEXPPROPIADetailComponent } from '../detail/tmcotizacionexppropia-detail.component';
import { TMCOTIZACIONEXPPROPIAUpdateComponent } from '../update/tmcotizacionexppropia-update.component';
import { TMCOTIZACIONEXPPROPIARoutingResolveService } from './tmcotizacionexppropia-routing-resolve.service';

const tMCOTIZACIONEXPPROPIARoute: Routes = [
  {
    path: '',
    component: TMCOTIZACIONEXPPROPIAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacionExpPropia/view',
    component: TMCOTIZACIONEXPPROPIADetailComponent,
    resolve: {
      tMCOTIZACIONEXPPROPIA: TMCOTIZACIONEXPPROPIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TMCOTIZACIONEXPPROPIAUpdateComponent,
    resolve: {
      tMCOTIZACIONEXPPROPIA: TMCOTIZACIONEXPPROPIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacionExpPropia/edit',
    component: TMCOTIZACIONEXPPROPIAUpdateComponent,
    resolve: {
      tMCOTIZACIONEXPPROPIA: TMCOTIZACIONEXPPROPIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tMCOTIZACIONEXPPROPIARoute)],
  exports: [RouterModule],
})
export class TMCOTIZACIONEXPPROPIARoutingModule {}
