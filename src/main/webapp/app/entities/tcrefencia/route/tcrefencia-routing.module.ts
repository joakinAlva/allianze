import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCREFENCIAComponent } from '../list/tcrefencia.component';
import { TCREFENCIADetailComponent } from '../detail/tcrefencia-detail.component';
import { TCREFENCIAUpdateComponent } from '../update/tcrefencia-update.component';
import { TCREFENCIARoutingResolveService } from './tcrefencia-routing-resolve.service';

const tCREFENCIARoute: Routes = [
  {
    path: '',
    component: TCREFENCIAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idReferencia/view',
    component: TCREFENCIADetailComponent,
    resolve: {
      tCREFENCIA: TCREFENCIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCREFENCIAUpdateComponent,
    resolve: {
      tCREFENCIA: TCREFENCIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idReferencia/edit',
    component: TCREFENCIAUpdateComponent,
    resolve: {
      tCREFENCIA: TCREFENCIARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCREFENCIARoute)],
  exports: [RouterModule],
})
export class TCREFENCIARoutingModule {}
