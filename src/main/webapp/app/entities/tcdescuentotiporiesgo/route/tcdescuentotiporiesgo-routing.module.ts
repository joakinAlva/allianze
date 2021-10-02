import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCDESCUENTOTIPORIESGOComponent } from '../list/tcdescuentotiporiesgo.component';
import { TCDESCUENTOTIPORIESGODetailComponent } from '../detail/tcdescuentotiporiesgo-detail.component';
import { TCDESCUENTOTIPORIESGOUpdateComponent } from '../update/tcdescuentotiporiesgo-update.component';
import { TCDESCUENTOTIPORIESGORoutingResolveService } from './tcdescuentotiporiesgo-routing-resolve.service';

const tCDESCUENTOTIPORIESGORoute: Routes = [
  {
    path: '',
    component: TCDESCUENTOTIPORIESGOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idDescuentoTipoRiesgo/view',
    component: TCDESCUENTOTIPORIESGODetailComponent,
    resolve: {
      tCDESCUENTOTIPORIESGO: TCDESCUENTOTIPORIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCDESCUENTOTIPORIESGOUpdateComponent,
    resolve: {
      tCDESCUENTOTIPORIESGO: TCDESCUENTOTIPORIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idDescuentoTipoRiesgo/edit',
    component: TCDESCUENTOTIPORIESGOUpdateComponent,
    resolve: {
      tCDESCUENTOTIPORIESGO: TCDESCUENTOTIPORIESGORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCDESCUENTOTIPORIESGORoute)],
  exports: [RouterModule],
})
export class TCDESCUENTOTIPORIESGORoutingModule {}
