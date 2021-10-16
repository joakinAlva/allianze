import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TMASEGURADOComponent } from '../list/tmasegurado.component';
import { TMASEGURADODetailComponent } from '../detail/tmasegurado-detail.component';
import { TMASEGURADOUpdateComponent } from '../update/tmasegurado-update.component';
import { TMASEGURADORoutingResolveService } from './tmasegurado-routing-resolve.service';

const tMASEGURADORoute: Routes = [
  {
    path: '',
    component: TMASEGURADOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idAsegurados/view',
    component: TMASEGURADODetailComponent,
    resolve: {
      tMASEGURADO: TMASEGURADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TMASEGURADOUpdateComponent,
    resolve: {
      tMASEGURADO: TMASEGURADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idAsegurados/edit',
    component: TMASEGURADOUpdateComponent,
    resolve: {
      tMASEGURADO: TMASEGURADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tMASEGURADORoute)],
  exports: [RouterModule],
})
export class TMASEGURADORoutingModule {}
