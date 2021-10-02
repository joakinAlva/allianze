import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCCONCEPTOComponent } from '../list/tcconcepto.component';
import { TCCONCEPTODetailComponent } from '../detail/tcconcepto-detail.component';
import { TCCONCEPTOUpdateComponent } from '../update/tcconcepto-update.component';
import { TCCONCEPTORoutingResolveService } from './tcconcepto-routing-resolve.service';

const tCCONCEPTORoute: Routes = [
  {
    path: '',
    component: TCCONCEPTOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idConcepto/view',
    component: TCCONCEPTODetailComponent,
    resolve: {
      tCCONCEPTO: TCCONCEPTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCCONCEPTOUpdateComponent,
    resolve: {
      tCCONCEPTO: TCCONCEPTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idConcepto/edit',
    component: TCCONCEPTOUpdateComponent,
    resolve: {
      tCCONCEPTO: TCCONCEPTORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCCONCEPTORoute)],
  exports: [RouterModule],
})
export class TCCONCEPTORoutingModule {}
