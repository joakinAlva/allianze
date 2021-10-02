import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCFACTORDESCUENTOComponent } from './list/tcfactordescuento.component';
import { TCFACTORDESCUENTODetailComponent } from './detail/tcfactordescuento-detail.component';
import { TCFACTORDESCUENTOUpdateComponent } from './update/tcfactordescuento-update.component';
import { TCFACTORDESCUENTODeleteDialogComponent } from './delete/tcfactordescuento-delete-dialog.component';
import { TCFACTORDESCUENTORoutingModule } from './route/tcfactordescuento-routing.module';

@NgModule({
  imports: [SharedModule, TCFACTORDESCUENTORoutingModule],
  declarations: [
    TCFACTORDESCUENTOComponent,
    TCFACTORDESCUENTODetailComponent,
    TCFACTORDESCUENTOUpdateComponent,
    TCFACTORDESCUENTODeleteDialogComponent,
  ],
  entryComponents: [TCFACTORDESCUENTODeleteDialogComponent],
})
export class TCFACTORDESCUENTOModule {}
