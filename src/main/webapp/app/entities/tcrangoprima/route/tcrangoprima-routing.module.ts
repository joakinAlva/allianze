import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCRANGOPRIMAComponent } from '../list/tcrangoprima.component';
import { TCRANGOPRIMADetailComponent } from '../detail/tcrangoprima-detail.component';
import { TCRANGOPRIMAUpdateComponent } from '../update/tcrangoprima-update.component';
import { TCRANGOPRIMARoutingResolveService } from './tcrangoprima-routing-resolve.service';

const tCRANGOPRIMARoute: Routes = [
  {
    path: '',
    component: TCRANGOPRIMAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRangoPrima/view',
    component: TCRANGOPRIMADetailComponent,
    resolve: {
      tCRANGOPRIMA: TCRANGOPRIMARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCRANGOPRIMAUpdateComponent,
    resolve: {
      tCRANGOPRIMA: TCRANGOPRIMARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRangoPrima/edit',
    component: TCRANGOPRIMAUpdateComponent,
    resolve: {
      tCRANGOPRIMA: TCRANGOPRIMARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCRANGOPRIMARoute)],
  exports: [RouterModule],
})
export class TCRANGOPRIMARoutingModule {}
