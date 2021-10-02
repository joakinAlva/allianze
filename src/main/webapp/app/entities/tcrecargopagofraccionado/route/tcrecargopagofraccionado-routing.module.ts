import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TCRECARGOPAGOFRACCIONADOComponent } from '../list/tcrecargopagofraccionado.component';
import { TCRECARGOPAGOFRACCIONADODetailComponent } from '../detail/tcrecargopagofraccionado-detail.component';
import { TCRECARGOPAGOFRACCIONADOUpdateComponent } from '../update/tcrecargopagofraccionado-update.component';
import { TCRECARGOPAGOFRACCIONADORoutingResolveService } from './tcrecargopagofraccionado-routing-resolve.service';

const tCRECARGOPAGOFRACCIONADORoute: Routes = [
  {
    path: '',
    component: TCRECARGOPAGOFRACCIONADOComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRecargoPagoFraccionado/view',
    component: TCRECARGOPAGOFRACCIONADODetailComponent,
    resolve: {
      tCRECARGOPAGOFRACCIONADO: TCRECARGOPAGOFRACCIONADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TCRECARGOPAGOFRACCIONADOUpdateComponent,
    resolve: {
      tCRECARGOPAGOFRACCIONADO: TCRECARGOPAGOFRACCIONADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':idRecargoPagoFraccionado/edit',
    component: TCRECARGOPAGOFRACCIONADOUpdateComponent,
    resolve: {
      tCRECARGOPAGOFRACCIONADO: TCRECARGOPAGOFRACCIONADORoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tCRECARGOPAGOFRACCIONADORoute)],
  exports: [RouterModule],
})
export class TCRECARGOPAGOFRACCIONADORoutingModule {}
