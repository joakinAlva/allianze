import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCTIPOPRODUCTOComponent } from '../list/tctipoproducto.component';
import { TCTIPOPRODUCTODetailComponent } from '../detail/tctipoproducto-detail.component';
import { TCTIPOPRODUCTOUpdateComponent } from '../update/tctipoproducto-update.component';
import { TCTIPOPRODUCTORoutingResolveService } from './tctipoproducto-routing-resolve.service';

const tCTIPOPRODUCTORoute: Routes = [
  {
    path: '',
    component: TCTIPOPRODUCTOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipoProducto/view',
    component: TCTIPOPRODUCTODetailComponent,
    resolve: {
      tCTIPOPRODUCTO: TCTIPOPRODUCTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCTIPOPRODUCTOUpdateComponent,
    resolve: {
      tCTIPOPRODUCTO: TCTIPOPRODUCTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipoProducto/edit',
    component: TCTIPOPRODUCTOUpdateComponent,
    resolve: {
      tCTIPOPRODUCTO: TCTIPOPRODUCTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCTIPOPRODUCTORoute)],
  exports: [RouterModule],
})
export class TCTIPOPRODUCTORoutingModule {}
