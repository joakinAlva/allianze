import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCPRIMANETASDESCComponent } from '../list/tcprimanetasdesc.component';
import { TCPRIMANETASDESCDetailComponent } from '../detail/tcprimanetasdesc-detail.component';
import { TCPRIMANETASDESCUpdateComponent } from '../update/tcprimanetasdesc-update.component';
import { TCPRIMANETASDESCRoutingResolveService } from './tcprimanetasdesc-routing-resolve.service';

const tCPRIMANETASDESCRoute: Routes = [
  {
    path: '',
    component: TCPRIMANETASDESCComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPrimaNetaSdesc/view',
    component: TCPRIMANETASDESCDetailComponent,
    resolve: {
      tCPRIMANETASDESC: TCPRIMANETASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCPRIMANETASDESCUpdateComponent,
    resolve: {
      tCPRIMANETASDESC: TCPRIMANETASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPrimaNetaSdesc/edit',
    component: TCPRIMANETASDESCUpdateComponent,
    resolve: {
      tCPRIMANETASDESC: TCPRIMANETASDESCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCPRIMANETASDESCRoute)],
  exports: [RouterModule],
})
export class TCPRIMANETASDESCRoutingModule {}
