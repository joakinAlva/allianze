import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCDESCUENTOSUMAASEGURADAComponent } from '../list/tcdescuentosumaasegurada.component';
import { TCDESCUENTOSUMAASEGURADADetailComponent } from '../detail/tcdescuentosumaasegurada-detail.component';
import { TCDESCUENTOSUMAASEGURADAUpdateComponent } from '../update/tcdescuentosumaasegurada-update.component';
import { TCDESCUENTOSUMAASEGURADARoutingResolveService } from './tcdescuentosumaasegurada-routing-resolve.service';

const tCDESCUENTOSUMAASEGURADARoute: Routes = [
  {
    path: '',
    component: TCDESCUENTOSUMAASEGURADAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idDescuentoSumaAsegurada/view',
    component: TCDESCUENTOSUMAASEGURADADetailComponent,
    resolve: {
      tCDESCUENTOSUMAASEGURADA: TCDESCUENTOSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCDESCUENTOSUMAASEGURADAUpdateComponent,
    resolve: {
      tCDESCUENTOSUMAASEGURADA: TCDESCUENTOSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idDescuentoSumaAsegurada/edit',
    component: TCDESCUENTOSUMAASEGURADAUpdateComponent,
    resolve: {
      tCDESCUENTOSUMAASEGURADA: TCDESCUENTOSUMAASEGURADARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCDESCUENTOSUMAASEGURADARoute)],
  exports: [RouterModule],
})
export class TCDESCUENTOSUMAASEGURADARoutingModule {}
