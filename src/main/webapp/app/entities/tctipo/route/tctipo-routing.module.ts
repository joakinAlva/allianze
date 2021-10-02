import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCTIPOComponent } from '../list/tctipo.component';
import { TCTIPODetailComponent } from '../detail/tctipo-detail.component';
import { TCTIPOUpdateComponent } from '../update/tctipo-update.component';
import { TCTIPORoutingResolveService } from './tctipo-routing-resolve.service';

const tCTIPORoute: Routes = [
  {
    path: '',
    component: TCTIPOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipo/view',
    component: TCTIPODetailComponent,
    resolve: {
      tCTIPO: TCTIPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCTIPOUpdateComponent,
    resolve: {
      tCTIPO: TCTIPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipo/edit',
    component: TCTIPOUpdateComponent,
    resolve: {
      tCTIPO: TCTIPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCTIPORoute)],
  exports: [RouterModule],
})
export class TCTIPORoutingModule {}
