import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCUOTAPROPUESTAComponent } from '../list/tccuotapropuesta.component';
import { TCCUOTAPROPUESTADetailComponent } from '../detail/tccuotapropuesta-detail.component';
import { TCCUOTAPROPUESTAUpdateComponent } from '../update/tccuotapropuesta-update.component';
import { TCCUOTAPROPUESTARoutingResolveService } from './tccuotapropuesta-routing-resolve.service';

const tCCUOTAPROPUESTARoute: Routes = [
  {
    path: '',
    component: TCCUOTAPROPUESTAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaPropuesta/view',
    component: TCCUOTAPROPUESTADetailComponent,
    resolve: {
      tCCUOTAPROPUESTA: TCCUOTAPROPUESTARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCUOTAPROPUESTAUpdateComponent,
    resolve: {
      tCCUOTAPROPUESTA: TCCUOTAPROPUESTARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaPropuesta/edit',
    component: TCCUOTAPROPUESTAUpdateComponent,
    resolve: {
      tCCUOTAPROPUESTA: TCCUOTAPROPUESTARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCUOTAPROPUESTARoute)],
  exports: [RouterModule],
})
export class TCCUOTAPROPUESTARoutingModule {}
