import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCESTATUSCOTIZACIONComponent } from './list/tcestatuscotizacion.component';
import { TCESTATUSCOTIZACIONDetailComponent } from './detail/tcestatuscotizacion-detail.component';
import { TCESTATUSCOTIZACIONUpdateComponent } from './update/tcestatuscotizacion-update.component';
import { TCESTATUSCOTIZACIONDeleteDialogComponent } from './delete/tcestatuscotizacion-delete-dialog.component';
import { TCESTATUSCOTIZACIONRoutingModule } from './route/tcestatuscotizacion-routing.module';

@NgModule({
  imports: [SharedModule, TCESTATUSCOTIZACIONRoutingModule],
  declarations: [
    TCESTATUSCOTIZACIONComponent,
    TCESTATUSCOTIZACIONDetailComponent,
    TCESTATUSCOTIZACIONUpdateComponent,
    TCESTATUSCOTIZACIONDeleteDialogComponent,
  ],
  entryComponents: [TCESTATUSCOTIZACIONDeleteDialogComponent],
})
export class TCESTATUSCOTIZACIONModule {}
