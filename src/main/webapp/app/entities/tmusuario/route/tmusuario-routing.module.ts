import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TMUSUARIOComponent } from '../list/tmusuario.component';
import { TMUSUARIODetailComponent } from '../detail/tmusuario-detail.component';
import { TMUSUARIOUpdateComponent } from '../update/tmusuario-update.component';
import { TMUSUARIORoutingResolveService } from './tmusuario-routing-resolve.service';

const tMUSUARIORoute: Routes = [
  {
    path: '',
    component: TMUSUARIOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idUsuario/view',
    component: TMUSUARIODetailComponent,
    resolve: {
      tMUSUARIO: TMUSUARIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TMUSUARIOUpdateComponent,
    resolve: {
      tMUSUARIO: TMUSUARIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idUsuario/edit',
    component: TMUSUARIOUpdateComponent,
    resolve: {
      tMUSUARIO: TMUSUARIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tMUSUARIORoute)],
  exports: [RouterModule],
})
export class TMUSUARIORoutingModule {}
