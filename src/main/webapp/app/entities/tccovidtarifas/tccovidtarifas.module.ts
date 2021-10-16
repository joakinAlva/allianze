import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCOVIDTARIFASComponent } from './list/tccovidtarifas.component';
import { TCCOVIDTARIFASDetailComponent } from './detail/tccovidtarifas-detail.component';
import { TCCOVIDTARIFASUpdateComponent } from './update/tccovidtarifas-update.component';
import { TCCOVIDTARIFASDeleteDialogComponent } from './delete/tccovidtarifas-delete-dialog.component';
import { TCCOVIDTARIFASRoutingModule } from './route/tccovidtarifas-routing.module';

@NgModule({
  imports: [SharedModule, TCCOVIDTARIFASRoutingModule],
  declarations: [
    TCCOVIDTARIFASComponent,
    TCCOVIDTARIFASDetailComponent,
    TCCOVIDTARIFASUpdateComponent,
    TCCOVIDTARIFASDeleteDialogComponent,
  ],
  entryComponents: [TCCOVIDTARIFASDeleteDialogComponent],
})
export class TCCOVIDTARIFASModule {}
