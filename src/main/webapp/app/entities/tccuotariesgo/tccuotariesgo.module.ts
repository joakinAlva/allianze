import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCUOTARIESGOComponent } from './list/tccuotariesgo.component';
import { TCCUOTARIESGODetailComponent } from './detail/tccuotariesgo-detail.component';
import { TCCUOTARIESGOUpdateComponent } from './update/tccuotariesgo-update.component';
import { TCCUOTARIESGODeleteDialogComponent } from './delete/tccuotariesgo-delete-dialog.component';
import { TCCUOTARIESGORoutingModule } from './route/tccuotariesgo-routing.module';

@NgModule({
  imports: [SharedModule, TCCUOTARIESGORoutingModule],
  declarations: [TCCUOTARIESGOComponent, TCCUOTARIESGODetailComponent, TCCUOTARIESGOUpdateComponent, TCCUOTARIESGODeleteDialogComponent],
  entryComponents: [TCCUOTARIESGODeleteDialogComponent],
})
export class TCCUOTARIESGOModule {}
