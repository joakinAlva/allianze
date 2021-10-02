import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCUOTATARIFASDESCComponent } from './list/tccuotatarifasdesc.component';
import { TCCUOTATARIFASDESCDetailComponent } from './detail/tccuotatarifasdesc-detail.component';
import { TCCUOTATARIFASDESCUpdateComponent } from './update/tccuotatarifasdesc-update.component';
import { TCCUOTATARIFASDESCDeleteDialogComponent } from './delete/tccuotatarifasdesc-delete-dialog.component';
import { TCCUOTATARIFASDESCRoutingModule } from './route/tccuotatarifasdesc-routing.module';

@NgModule({
  imports: [SharedModule, TCCUOTATARIFASDESCRoutingModule],
  declarations: [
    TCCUOTATARIFASDESCComponent,
    TCCUOTATARIFASDESCDetailComponent,
    TCCUOTATARIFASDESCUpdateComponent,
    TCCUOTATARIFASDESCDeleteDialogComponent,
  ],
  entryComponents: [TCCUOTATARIFASDESCDeleteDialogComponent],
})
export class TCCUOTATARIFASDESCModule {}
