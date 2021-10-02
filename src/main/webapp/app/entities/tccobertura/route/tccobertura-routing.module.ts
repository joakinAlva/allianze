import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCOBERTURAComponent } from '../list/tccobertura.component';
import { TCCOBERTURADetailComponent } from '../detail/tccobertura-detail.component';
import { TCCOBERTURAUpdateComponent } from '../update/tccobertura-update.component';
import { TCCOBERTURARoutingResolveService } from './tccobertura-routing-resolve.service';

const tCCOBERTURARoute: Routes = [
  {
    path: '',
    component: TCCOBERTURAComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCobertura/view',
    component: TCCOBERTURADetailComponent,
    resolve: {
      tCCOBERTURA: TCCOBERTURARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCOBERTURAUpdateComponent,
    resolve: {
      tCCOBERTURA: TCCOBERTURARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idCobertura/edit',
    component: TCCOBERTURAUpdateComponent,
    resolve: {
      tCCOBERTURA: TCCOBERTURARoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCOBERTURARoute)],
  exports: [RouterModule],
})
export class TCCOBERTURARoutingModule {}
