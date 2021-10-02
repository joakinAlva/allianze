import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCFACTORSAMIComponent } from '../list/tcfactorsami.component';
import { TCFACTORSAMIDetailComponent } from '../detail/tcfactorsami-detail.component';
import { TCFACTORSAMIUpdateComponent } from '../update/tcfactorsami-update.component';
import { TCFACTORSAMIRoutingResolveService } from './tcfactorsami-routing-resolve.service';

const tCFACTORSAMIRoute: Routes = [
  {
    path: '',
    component: TCFACTORSAMIComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idFactorSami/view',
    component: TCFACTORSAMIDetailComponent,
    resolve: {
      tCFACTORSAMI: TCFACTORSAMIRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCFACTORSAMIUpdateComponent,
    resolve: {
      tCFACTORSAMI: TCFACTORSAMIRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idFactorSami/edit',
    component: TCFACTORSAMIUpdateComponent,
    resolve: {
      tCFACTORSAMI: TCFACTORSAMIRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCFACTORSAMIRoute)],
  exports: [RouterModule],
})
export class TCFACTORSAMIRoutingModule {}
