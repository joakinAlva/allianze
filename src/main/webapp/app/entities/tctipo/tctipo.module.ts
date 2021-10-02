import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCTIPOComponent } from './list/tctipo.component';
import { TCTIPODetailComponent } from './detail/tctipo-detail.component';
import { TCTIPOUpdateComponent } from './update/tctipo-update.component';
import { TCTIPODeleteDialogComponent } from './delete/tctipo-delete-dialog.component';
import { TCTIPORoutingModule } from './route/tctipo-routing.module';

@NgModule({
  imports: [SharedModule, TCTIPORoutingModule],
  declarations: [TCTIPOComponent, TCTIPODetailComponent, TCTIPOUpdateComponent, TCTIPODeleteDialogComponent],
  entryComponents: [TCTIPODeleteDialogComponent],
})
export class TCTIPOModule {}
