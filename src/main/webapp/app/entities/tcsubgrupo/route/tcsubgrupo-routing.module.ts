import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCSUBGRUPOComponent } from '../list/tcsubgrupo.component';
import { TCSUBGRUPODetailComponent } from '../detail/tcsubgrupo-detail.component';
import { TCSUBGRUPOUpdateComponent } from '../update/tcsubgrupo-update.component';
import { TCSUBGRUPORoutingResolveService } from './tcsubgrupo-routing-resolve.service';

const tCSUBGRUPORoute: Routes = [
  {
    path: '',
    component: TCSUBGRUPOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idSubGrupo/view',
    component: TCSUBGRUPODetailComponent,
    resolve: {
      tCSUBGRUPO: TCSUBGRUPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCSUBGRUPOUpdateComponent,
    resolve: {
      tCSUBGRUPO: TCSUBGRUPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idSubGrupo/edit',
    component: TCSUBGRUPOUpdateComponent,
    resolve: {
      tCSUBGRUPO: TCSUBGRUPORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCSUBGRUPORoute)],
  exports: [RouterModule],
})
export class TCSUBGRUPORoutingModule {}
