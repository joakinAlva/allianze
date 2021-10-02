import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCOVIDComponent } from './list/tccovid.component';
import { TCCOVIDDetailComponent } from './detail/tccovid-detail.component';
import { TCCOVIDUpdateComponent } from './update/tccovid-update.component';
import { TCCOVIDDeleteDialogComponent } from './delete/tccovid-delete-dialog.component';
import { TCCOVIDRoutingModule } from './route/tccovid-routing.module';

@NgModule({
  imports: [SharedModule, TCCOVIDRoutingModule],
  declarations: [TCCOVIDComponent, TCCOVIDDetailComponent, TCCOVIDUpdateComponent, TCCOVIDDeleteDialogComponent],
  entryComponents: [TCCOVIDDeleteDialogComponent],
})
export class TCCOVIDModule {}
