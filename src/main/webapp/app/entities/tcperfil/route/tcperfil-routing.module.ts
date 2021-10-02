import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCPERFILComponent } from '../list/tcperfil.component';
import { TCPERFILDetailComponent } from '../detail/tcperfil-detail.component';
import { TCPERFILUpdateComponent } from '../update/tcperfil-update.component';
import { TCPERFILRoutingResolveService } from './tcperfil-routing-resolve.service';

const tCPERFILRoute: Routes = [
  {
    path: '',
    component: TCPERFILComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPerfil/view',
    component: TCPERFILDetailComponent,
    resolve: {
      tCPERFIL: TCPERFILRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCPERFILUpdateComponent,
    resolve: {
      tCPERFIL: TCPERFILRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idPerfil/edit',
    component: TCPERFILUpdateComponent,
    resolve: {
      tCPERFIL: TCPERFILRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCPERFILRoute)],
  exports: [RouterModule],
})
export class TCPERFILRoutingModule {}
