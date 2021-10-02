import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCUNIDADNEGOCIOComponent } from '../list/tcunidadnegocio.component';
import { TCUNIDADNEGOCIODetailComponent } from '../detail/tcunidadnegocio-detail.component';
import { TCUNIDADNEGOCIOUpdateComponent } from '../update/tcunidadnegocio-update.component';
import { TCUNIDADNEGOCIORoutingResolveService } from './tcunidadnegocio-routing-resolve.service';

const tCUNIDADNEGOCIORoute: Routes = [
  {
    path: '',
    component: TCUNIDADNEGOCIOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idUnidadNegocio/view',
    component: TCUNIDADNEGOCIODetailComponent,
    resolve: {
      tCUNIDADNEGOCIO: TCUNIDADNEGOCIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCUNIDADNEGOCIOUpdateComponent,
    resolve: {
      tCUNIDADNEGOCIO: TCUNIDADNEGOCIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idUnidadNegocio/edit',
    component: TCUNIDADNEGOCIOUpdateComponent,
    resolve: {
      tCUNIDADNEGOCIO: TCUNIDADNEGOCIORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCUNIDADNEGOCIORoute)],
  exports: [RouterModule],
})
export class TCUNIDADNEGOCIORoutingModule {}
