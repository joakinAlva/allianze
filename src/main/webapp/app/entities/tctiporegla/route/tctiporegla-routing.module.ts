import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCTIPOREGLAComponent } from '../list/tctiporegla.component';
import { TCTIPOREGLADetailComponent } from '../detail/tctiporegla-detail.component';
import { TCTIPOREGLAUpdateComponent } from '../update/tctiporegla-update.component';
import { TCTIPOREGLARoutingResolveService } from './tctiporegla-routing-resolve.service';

const tCTIPOREGLARoute: Routes = [
  {
    path: '',
    component: TCTIPOREGLAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipoRegla/view',
    component: TCTIPOREGLADetailComponent,
    resolve: {
      tCTIPOREGLA: TCTIPOREGLARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCTIPOREGLAUpdateComponent,
    resolve: {
      tCTIPOREGLA: TCTIPOREGLARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idTipoRegla/edit',
    component: TCTIPOREGLAUpdateComponent,
    resolve: {
      tCTIPOREGLA: TCTIPOREGLARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCTIPOREGLARoute)],
  exports: [RouterModule],
})
export class TCTIPOREGLARoutingModule {}
