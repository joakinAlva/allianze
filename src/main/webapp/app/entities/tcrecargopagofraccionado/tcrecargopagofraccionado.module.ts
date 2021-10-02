import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCRECARGOPAGOFRACCIONADOComponent } from './list/tcrecargopagofraccionado.component';
import { TCRECARGOPAGOFRACCIONADODetailComponent } from './detail/tcrecargopagofraccionado-detail.component';
import { TCRECARGOPAGOFRACCIONADOUpdateComponent } from './update/tcrecargopagofraccionado-update.component';
import { TCRECARGOPAGOFRACCIONADODeleteDialogComponent } from './delete/tcrecargopagofraccionado-delete-dialog.component';
import { TCRECARGOPAGOFRACCIONADORoutingModule } from './route/tcrecargopagofraccionado-routing.module';

@NgModule({
  imports: [SharedModule, TCRECARGOPAGOFRACCIONADORoutingModule],
  declarations: [
    TCRECARGOPAGOFRACCIONADOComponent,
    TCRECARGOPAGOFRACCIONADODetailComponent,
    TCRECARGOPAGOFRACCIONADOUpdateComponent,
    TCRECARGOPAGOFRACCIONADODeleteDialogComponent,
  ],
  entryComponents: [TCRECARGOPAGOFRACCIONADODeleteDialogComponent],
})
export class TCRECARGOPAGOFRACCIONADOModule {}
