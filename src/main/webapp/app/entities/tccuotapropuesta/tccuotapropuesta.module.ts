import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCUOTAPROPUESTAComponent } from './list/tccuotapropuesta.component';
import { TCCUOTAPROPUESTADetailComponent } from './detail/tccuotapropuesta-detail.component';
import { TCCUOTAPROPUESTAUpdateComponent } from './update/tccuotapropuesta-update.component';
import { TCCUOTAPROPUESTADeleteDialogComponent } from './delete/tccuotapropuesta-delete-dialog.component';
import { TCCUOTAPROPUESTARoutingModule } from './route/tccuotapropuesta-routing.module';

@NgModule({
  imports: [SharedModule, TCCUOTAPROPUESTARoutingModule],
  declarations: [
    TCCUOTAPROPUESTAComponent,
    TCCUOTAPROPUESTADetailComponent,
    TCCUOTAPROPUESTAUpdateComponent,
    TCCUOTAPROPUESTADeleteDialogComponent,
  ],
  entryComponents: [TCCUOTAPROPUESTADeleteDialogComponent],
})
export class TCCUOTAPROPUESTAModule {}
