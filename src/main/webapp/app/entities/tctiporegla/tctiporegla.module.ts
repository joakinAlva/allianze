import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCTIPOREGLAComponent } from './list/tctiporegla.component';
import { TCTIPOREGLADetailComponent } from './detail/tctiporegla-detail.component';
import { TCTIPOREGLAUpdateComponent } from './update/tctiporegla-update.component';
import { TCTIPOREGLADeleteDialogComponent } from './delete/tctiporegla-delete-dialog.component';
import { TCTIPOREGLARoutingModule } from './route/tctiporegla-routing.module';

@NgModule({
  imports: [SharedModule, TCTIPOREGLARoutingModule],
  declarations: [TCTIPOREGLAComponent, TCTIPOREGLADetailComponent, TCTIPOREGLAUpdateComponent, TCTIPOREGLADeleteDialogComponent],
  entryComponents: [TCTIPOREGLADeleteDialogComponent],
})
export class TCTIPOREGLAModule {}
