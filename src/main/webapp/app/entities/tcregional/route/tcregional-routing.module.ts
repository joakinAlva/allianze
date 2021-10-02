import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCREGIONALComponent } from '../list/tcregional.component';
import { TCREGIONALDetailComponent } from '../detail/tcregional-detail.component';
import { TCREGIONALUpdateComponent } from '../update/tcregional-update.component';
import { TCREGIONALRoutingResolveService } from './tcregional-routing-resolve.service';

const tCREGIONALRoute: Routes = [
  {
    path: '',
    component: TCREGIONALComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRegional/view',
    component: TCREGIONALDetailComponent,
    resolve: {
      tCREGIONAL: TCREGIONALRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCREGIONALUpdateComponent,
    resolve: {
      tCREGIONAL: TCREGIONALRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRegional/edit',
    component: TCREGIONALUpdateComponent,
    resolve: {
      tCREGIONAL: TCREGIONALRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCREGIONALRoute)],
  exports: [RouterModule],
})
export class TCREGIONALRoutingModule {}
