import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCOVIDComponent } from '../list/tccovid.component';
import { TCCOVIDDetailComponent } from '../detail/tccovid-detail.component';
import { TCCOVIDUpdateComponent } from '../update/tccovid-update.component';
import { TCCOVIDRoutingResolveService } from './tccovid-routing-resolve.service';

const tCCOVIDRoute: Routes = [
  {
    path: '',
    component: TCCOVIDComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCovid/view',
    component: TCCOVIDDetailComponent,
    resolve: {
      tCCOVID: TCCOVIDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCOVIDUpdateComponent,
    resolve: {
      tCCOVID: TCCOVIDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCovid/edit',
    component: TCCOVIDUpdateComponent,
    resolve: {
      tCCOVID: TCCOVIDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCOVIDRoute)],
  exports: [RouterModule],
})
export class TCCOVIDRoutingModule {}
