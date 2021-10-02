import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCDESCUENTOSUMAASEGURADAComponent } from './list/tcdescuentosumaasegurada.component';
import { TCDESCUENTOSUMAASEGURADADetailComponent } from './detail/tcdescuentosumaasegurada-detail.component';
import { TCDESCUENTOSUMAASEGURADAUpdateComponent } from './update/tcdescuentosumaasegurada-update.component';
import { TCDESCUENTOSUMAASEGURADADeleteDialogComponent } from './delete/tcdescuentosumaasegurada-delete-dialog.component';
import { TCDESCUENTOSUMAASEGURADARoutingModule } from './route/tcdescuentosumaasegurada-routing.module';

@NgModule({
  imports: [SharedModule, TCDESCUENTOSUMAASEGURADARoutingModule],
  declarations: [
    TCDESCUENTOSUMAASEGURADAComponent,
    TCDESCUENTOSUMAASEGURADADetailComponent,
    TCDESCUENTOSUMAASEGURADAUpdateComponent,
    TCDESCUENTOSUMAASEGURADADeleteDialogComponent,
  ],
  entryComponents: [TCDESCUENTOSUMAASEGURADADeleteDialogComponent],
})
export class TCDESCUENTOSUMAASEGURADAModule {}
