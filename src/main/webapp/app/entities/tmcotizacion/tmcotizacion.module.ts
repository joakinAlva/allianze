import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TMCOTIZACIONComponent } from './list/tmcotizacion.component';
import { TMCOTIZACIONDetailComponent } from './detail/tmcotizacion-detail.component';
import { TMCOTIZACIONUpdateComponent } from './update/tmcotizacion-update.component';
import { TMCOTIZACIONDeleteDialogComponent } from './delete/tmcotizacion-delete-dialog.component';
import { TMCOTIZACIONRoutingModule } from './route/tmcotizacion-routing.module';

@NgModule({
  imports: [SharedModule, TMCOTIZACIONRoutingModule],
  declarations: [TMCOTIZACIONComponent, TMCOTIZACIONDetailComponent, TMCOTIZACIONUpdateComponent, TMCOTIZACIONDeleteDialogComponent],
  entryComponents: [TMCOTIZACIONDeleteDialogComponent],
})
export class TMCOTIZACIONModule {}
