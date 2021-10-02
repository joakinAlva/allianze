import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCUOTAVALORComponent } from '../list/tccuotavalor.component';
import { TCCUOTAVALORDetailComponent } from '../detail/tccuotavalor-detail.component';
import { TCCUOTAVALORUpdateComponent } from '../update/tccuotavalor-update.component';
import { TCCUOTAVALORRoutingResolveService } from './tccuotavalor-routing-resolve.service';

const tCCUOTAVALORRoute: Routes = [
  {
    path: '',
    component: TCCUOTAVALORComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaValor/view',
    component: TCCUOTAVALORDetailComponent,
    resolve: {
      tCCUOTAVALOR: TCCUOTAVALORRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCUOTAVALORUpdateComponent,
    resolve: {
      tCCUOTAVALOR: TCCUOTAVALORRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCuotaValor/edit',
    component: TCCUOTAVALORUpdateComponent,
    resolve: {
      tCCUOTAVALOR: TCCUOTAVALORRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCUOTAVALORRoute)],
  exports: [RouterModule],
})
export class TCCUOTAVALORRoutingModule {}
