import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCOCUPACIONComponent } from '../list/tcocupacion.component';
import { TCOCUPACIONDetailComponent } from '../detail/tcocupacion-detail.component';
import { TCOCUPACIONUpdateComponent } from '../update/tcocupacion-update.component';
import { TCOCUPACIONRoutingResolveService } from './tcocupacion-routing-resolve.service';

const tCOCUPACIONRoute: Routes = [
  {
    path: '',
    component: TCOCUPACIONComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idOcupacion/view',
    component: TCOCUPACIONDetailComponent,
    resolve: {
      tCOCUPACION: TCOCUPACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCOCUPACIONUpdateComponent,
    resolve: {
      tCOCUPACION: TCOCUPACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idOcupacion/edit',
    component: TCOCUPACIONUpdateComponent,
    resolve: {
      tCOCUPACION: TCOCUPACIONRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCOCUPACIONRoute)],
  exports: [RouterModule],
})
export class TCOCUPACIONRoutingModule {}
