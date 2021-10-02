import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCFACTORDESCUENTOComponent } from '../list/tcfactordescuento.component';
import { TCFACTORDESCUENTODetailComponent } from '../detail/tcfactordescuento-detail.component';
import { TCFACTORDESCUENTOUpdateComponent } from '../update/tcfactordescuento-update.component';
import { TCFACTORDESCUENTORoutingResolveService } from './tcfactordescuento-routing-resolve.service';

const tCFACTORDESCUENTORoute: Routes = [
  {
    path: '',
    component: TCFACTORDESCUENTOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idFactorDescuento/view',
    component: TCFACTORDESCUENTODetailComponent,
    resolve: {
      tCFACTORDESCUENTO: TCFACTORDESCUENTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCFACTORDESCUENTOUpdateComponent,
    resolve: {
      tCFACTORDESCUENTO: TCFACTORDESCUENTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idFactorDescuento/edit',
    component: TCFACTORDESCUENTOUpdateComponent,
    resolve: {
      tCFACTORDESCUENTO: TCFACTORDESCUENTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCFACTORDESCUENTORoute)],
  exports: [RouterModule],
})
export class TCFACTORDESCUENTORoutingModule {}
