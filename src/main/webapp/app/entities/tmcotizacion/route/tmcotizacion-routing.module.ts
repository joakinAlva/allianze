import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TMCOTIZACIONComponent } from '../list/tmcotizacion.component';
import { TMCOTIZACIONDetailComponent } from '../detail/tmcotizacion-detail.component';
import { TMCOTIZACIONUpdateComponent } from '../update/tmcotizacion-update.component';
import { TMCOTIZACIONRoutingResolveService } from './tmcotizacion-routing-resolve.service';

const tMCOTIZACIONRoute: Routes = [
  {
    path: '',
    component: TMCOTIZACIONComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacion/view',
    component: TMCOTIZACIONDetailComponent,
    resolve: {
      tMCOTIZACION: TMCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TMCOTIZACIONUpdateComponent,
    resolve: {
      tMCOTIZACION: TMCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCotizacion/edit',
    component: TMCOTIZACIONUpdateComponent,
    resolve: {
      tMCOTIZACION: TMCOTIZACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tMCOTIZACIONRoute)],
  exports: [RouterModule],
})
export class TMCOTIZACIONRoutingModule {}
