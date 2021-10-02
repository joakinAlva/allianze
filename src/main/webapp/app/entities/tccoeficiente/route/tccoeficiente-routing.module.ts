import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCOEFICIENTEComponent } from '../list/tccoeficiente.component';
import { TCCOEFICIENTEDetailComponent } from '../detail/tccoeficiente-detail.component';
import { TCCOEFICIENTEUpdateComponent } from '../update/tccoeficiente-update.component';
import { TCCOEFICIENTERoutingResolveService } from './tccoeficiente-routing-resolve.service';

const tCCOEFICIENTERoute: Routes = [
  {
    path: '',
    component: TCCOEFICIENTEComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCoeficiente/view',
    component: TCCOEFICIENTEDetailComponent,
    resolve: {
      tCCOEFICIENTE: TCCOEFICIENTERoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCOEFICIENTEUpdateComponent,
    resolve: {
      tCCOEFICIENTE: TCCOEFICIENTERoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCoeficiente/edit',
    component: TCCOEFICIENTEUpdateComponent,
    resolve: {
      tCCOEFICIENTE: TCCOEFICIENTERoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCOEFICIENTERoute)],
  exports: [RouterModule],
})
export class TCCOEFICIENTERoutingModule {}
