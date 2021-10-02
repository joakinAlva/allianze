import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCHIPOTESISComponent } from '../list/tchipotesis.component';
import { TCHIPOTESISDetailComponent } from '../detail/tchipotesis-detail.component';
import { TCHIPOTESISUpdateComponent } from '../update/tchipotesis-update.component';
import { TCHIPOTESISRoutingResolveService } from './tchipotesis-routing-resolve.service';

const tCHIPOTESISRoute: Routes = [
  {
    path: '',
    component: TCHIPOTESISComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idHipotesis/view',
    component: TCHIPOTESISDetailComponent,
    resolve: {
      tCHIPOTESIS: TCHIPOTESISRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCHIPOTESISUpdateComponent,
    resolve: {
      tCHIPOTESIS: TCHIPOTESISRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idHipotesis/edit',
    component: TCHIPOTESISUpdateComponent,
    resolve: {
      tCHIPOTESIS: TCHIPOTESISRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCHIPOTESISRoute)],
  exports: [RouterModule],
})
export class TCHIPOTESISRoutingModule {}
