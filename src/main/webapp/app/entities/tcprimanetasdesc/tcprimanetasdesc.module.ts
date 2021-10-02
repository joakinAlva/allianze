import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCPRIMANETASDESCComponent } from './list/tcprimanetasdesc.component';
import { TCPRIMANETASDESCDetailComponent } from './detail/tcprimanetasdesc-detail.component';
import { TCPRIMANETASDESCUpdateComponent } from './update/tcprimanetasdesc-update.component';
import { TCPRIMANETASDESCDeleteDialogComponent } from './delete/tcprimanetasdesc-delete-dialog.component';
import { TCPRIMANETASDESCRoutingModule } from './route/tcprimanetasdesc-routing.module';

@NgModule({
  imports: [SharedModule, TCPRIMANETASDESCRoutingModule],
  declarations: [
    TCPRIMANETASDESCComponent,
    TCPRIMANETASDESCDetailComponent,
    TCPRIMANETASDESCUpdateComponent,
    TCPRIMANETASDESCDeleteDialogComponent,
  ],
  entryComponents: [TCPRIMANETASDESCDeleteDialogComponent],
})
export class TCPRIMANETASDESCModule {}
