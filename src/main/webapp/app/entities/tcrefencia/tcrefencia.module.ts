import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCREFENCIAComponent } from './list/tcrefencia.component';
import { TCREFENCIADetailComponent } from './detail/tcrefencia-detail.component';
import { TCREFENCIAUpdateComponent } from './update/tcrefencia-update.component';
import { TCREFENCIADeleteDialogComponent } from './delete/tcrefencia-delete-dialog.component';
import { TCREFENCIARoutingModule } from './route/tcrefencia-routing.module';

@NgModule({
  imports: [SharedModule, TCREFENCIARoutingModule],
  declarations: [TCREFENCIAComponent, TCREFENCIADetailComponent, TCREFENCIAUpdateComponent, TCREFENCIADeleteDialogComponent],
  entryComponents: [TCREFENCIADeleteDialogComponent],
})
export class TCREFENCIAModule {}
