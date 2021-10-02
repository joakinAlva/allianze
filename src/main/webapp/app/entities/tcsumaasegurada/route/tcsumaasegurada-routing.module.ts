import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCSUMAASEGURADAComponent } from '../list/tcsumaasegurada.component';
import { TCSUMAASEGURADADetailComponent } from '../detail/tcsumaasegurada-detail.component';
import { TCSUMAASEGURADAUpdateComponent } from '../update/tcsumaasegurada-update.component';
import { TCSUMAASEGURADARoutingResolveService } from './tcsumaasegurada-routing-resolve.service';

const tCSUMAASEGURADARoute: Routes = [
  {
    path: '',
    component: TCSUMAASEGURADAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idSumaAsegurada/view',
    component: TCSUMAASEGURADADetailComponent,
    resolve: {
      tCSUMAASEGURADA: TCSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCSUMAASEGURADAUpdateComponent,
    resolve: {
      tCSUMAASEGURADA: TCSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idSumaAsegurada/edit',
    component: TCSUMAASEGURADAUpdateComponent,
    resolve: {
      tCSUMAASEGURADA: TCSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCSUMAASEGURADARoute)],
  exports: [RouterModule],
})
export class TCSUMAASEGURADARoutingModule {}
