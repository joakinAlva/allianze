import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCEDADRECARGOComponent } from '../list/tcedadrecargo.component';
import { TCEDADRECARGODetailComponent } from '../detail/tcedadrecargo-detail.component';
import { TCEDADRECARGOUpdateComponent } from '../update/tcedadrecargo-update.component';
import { TCEDADRECARGORoutingResolveService } from './tcedadrecargo-routing-resolve.service';

const tCEDADRECARGORoute: Routes = [
  {
    path: '',
    component: TCEDADRECARGOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEdadRecargo/view',
    component: TCEDADRECARGODetailComponent,
    resolve: {
      tCEDADRECARGO: TCEDADRECARGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCEDADRECARGOUpdateComponent,
    resolve: {
      tCEDADRECARGO: TCEDADRECARGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idEdadRecargo/edit',
    component: TCEDADRECARGOUpdateComponent,
    resolve: {
      tCEDADRECARGO: TCEDADRECARGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCEDADRECARGORoute)],
  exports: [RouterModule],
})
export class TCEDADRECARGORoutingModule {}
