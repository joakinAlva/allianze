import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCOVIDTARIFASComponent } from '../list/tccovidtarifas.component';
import { TCCOVIDTARIFASDetailComponent } from '../detail/tccovidtarifas-detail.component';
import { TCCOVIDTARIFASUpdateComponent } from '../update/tccovidtarifas-update.component';
import { TCCOVIDTARIFASRoutingResolveService } from './tccovidtarifas-routing-resolve.service';

const tCCOVIDTARIFASRoute: Routes = [
  {
    path: '',
    component: TCCOVIDTARIFASComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCovidTarifas/view',
    component: TCCOVIDTARIFASDetailComponent,
    resolve: {
      tCCOVIDTARIFAS: TCCOVIDTARIFASRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCOVIDTARIFASUpdateComponent,
    resolve: {
      tCCOVIDTARIFAS: TCCOVIDTARIFASRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCovidTarifas/edit',
    component: TCCOVIDTARIFASUpdateComponent,
    resolve: {
      tCCOVIDTARIFAS: TCCOVIDTARIFASRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCOVIDTARIFASRoute)],
  exports: [RouterModule],
})
export class TCCOVIDTARIFASRoutingModule {}
