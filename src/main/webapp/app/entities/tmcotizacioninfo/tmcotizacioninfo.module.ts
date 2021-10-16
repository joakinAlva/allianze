import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TMCOTIZACIONINFOComponent } from './list/tmcotizacioninfo.component';
import { TMCOTIZACIONINFODetailComponent } from './detail/tmcotizacioninfo-detail.component';
import { TMCOTIZACIONINFOUpdateComponent } from './update/tmcotizacioninfo-update.component';
import { TMCOTIZACIONINFODeleteDialogComponent } from './delete/tmcotizacioninfo-delete-dialog.component';
import { TMCOTIZACIONINFORoutingModule } from './route/tmcotizacioninfo-routing.module';

@NgModule({
  imports: [SharedModule, TMCOTIZACIONINFORoutingModule],
  declarations: [
    TMCOTIZACIONINFOComponent,
    TMCOTIZACIONINFODetailComponent,
    TMCOTIZACIONINFOUpdateComponent,
    TMCOTIZACIONINFODeleteDialogComponent,
  ],
  entryComponents: [TMCOTIZACIONINFODeleteDialogComponent],
})
export class TMCOTIZACIONINFOModule {}
