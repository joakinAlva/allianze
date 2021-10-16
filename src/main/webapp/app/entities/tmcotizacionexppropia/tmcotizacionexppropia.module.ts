import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TMCOTIZACIONEXPPROPIAComponent } from './list/tmcotizacionexppropia.component';
import { TMCOTIZACIONEXPPROPIADetailComponent } from './detail/tmcotizacionexppropia-detail.component';
import { TMCOTIZACIONEXPPROPIAUpdateComponent } from './update/tmcotizacionexppropia-update.component';
import { TMCOTIZACIONEXPPROPIADeleteDialogComponent } from './delete/tmcotizacionexppropia-delete-dialog.component';
import { TMCOTIZACIONEXPPROPIARoutingModule } from './route/tmcotizacionexppropia-routing.module';

@NgModule({
  imports: [SharedModule, TMCOTIZACIONEXPPROPIARoutingModule],
  declarations: [
    TMCOTIZACIONEXPPROPIAComponent,
    TMCOTIZACIONEXPPROPIADetailComponent,
    TMCOTIZACIONEXPPROPIAUpdateComponent,
    TMCOTIZACIONEXPPROPIADeleteDialogComponent,
  ],
  entryComponents: [TMCOTIZACIONEXPPROPIADeleteDialogComponent],
})
export class TMCOTIZACIONEXPPROPIAModule {}
