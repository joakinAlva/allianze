import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCDESCUENTOTIPORIESGOComponent } from './list/tcdescuentotiporiesgo.component';
import { TCDESCUENTOTIPORIESGODetailComponent } from './detail/tcdescuentotiporiesgo-detail.component';
import { TCDESCUENTOTIPORIESGOUpdateComponent } from './update/tcdescuentotiporiesgo-update.component';
import { TCDESCUENTOTIPORIESGODeleteDialogComponent } from './delete/tcdescuentotiporiesgo-delete-dialog.component';
import { TCDESCUENTOTIPORIESGORoutingModule } from './route/tcdescuentotiporiesgo-routing.module';

@NgModule({
  imports: [SharedModule, TCDESCUENTOTIPORIESGORoutingModule],
  declarations: [
    TCDESCUENTOTIPORIESGOComponent,
    TCDESCUENTOTIPORIESGODetailComponent,
    TCDESCUENTOTIPORIESGOUpdateComponent,
    TCDESCUENTOTIPORIESGODeleteDialogComponent,
  ],
  entryComponents: [TCDESCUENTOTIPORIESGODeleteDialogComponent],
})
export class TCDESCUENTOTIPORIESGOModule {}
