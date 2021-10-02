import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCPRIMATARIFAComponent } from '../list/tcprimatarifa.component';
import { TCPRIMATARIFADetailComponent } from '../detail/tcprimatarifa-detail.component';
import { TCPRIMATARIFAUpdateComponent } from '../update/tcprimatarifa-update.component';
import { TCPRIMATARIFARoutingResolveService } from './tcprimatarifa-routing-resolve.service';

const tCPRIMATARIFARoute: Routes = [
  {
    path: '',
    component: TCPRIMATARIFAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPrimaTarifa/view',
    component: TCPRIMATARIFADetailComponent,
    resolve: {
      tCPRIMATARIFA: TCPRIMATARIFARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCPRIMATARIFAUpdateComponent,
    resolve: {
      tCPRIMATARIFA: TCPRIMATARIFARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPrimaTarifa/edit',
    component: TCPRIMATARIFAUpdateComponent,
    resolve: {
      tCPRIMATARIFA: TCPRIMATARIFARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCPRIMATARIFARoute)],
  exports: [RouterModule],
})
export class TCPRIMATARIFARoutingModule {}
