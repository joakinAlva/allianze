import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCPRIMATARIFAComponent } from './list/tcprimatarifa.component';
import { TCPRIMATARIFADetailComponent } from './detail/tcprimatarifa-detail.component';
import { TCPRIMATARIFAUpdateComponent } from './update/tcprimatarifa-update.component';
import { TCPRIMATARIFADeleteDialogComponent } from './delete/tcprimatarifa-delete-dialog.component';
import { TCPRIMATARIFARoutingModule } from './route/tcprimatarifa-routing.module';

@NgModule({
  imports: [SharedModule, TCPRIMATARIFARoutingModule],
  declarations: [TCPRIMATARIFAComponent, TCPRIMATARIFADetailComponent, TCPRIMATARIFAUpdateComponent, TCPRIMATARIFADeleteDialogComponent],
  entryComponents: [TCPRIMATARIFADeleteDialogComponent],
})
export class TCPRIMATARIFAModule {}
