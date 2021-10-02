import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCPERFILComponent } from './list/tcperfil.component';
import { TCPERFILDetailComponent } from './detail/tcperfil-detail.component';
import { TCPERFILUpdateComponent } from './update/tcperfil-update.component';
import { TCPERFILDeleteDialogComponent } from './delete/tcperfil-delete-dialog.component';
import { TCPERFILRoutingModule } from './route/tcperfil-routing.module';

@NgModule({
  imports: [SharedModule, TCPERFILRoutingModule],
  declarations: [TCPERFILComponent, TCPERFILDetailComponent, TCPERFILUpdateComponent, TCPERFILDeleteDialogComponent],
  entryComponents: [TCPERFILDeleteDialogComponent],
})
export class TCPERFILModule {}
