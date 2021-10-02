import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCRANGOPRIMAComponent } from './list/tcrangoprima.component';
import { TCRANGOPRIMADetailComponent } from './detail/tcrangoprima-detail.component';
import { TCRANGOPRIMAUpdateComponent } from './update/tcrangoprima-update.component';
import { TCRANGOPRIMADeleteDialogComponent } from './delete/tcrangoprima-delete-dialog.component';
import { TCRANGOPRIMARoutingModule } from './route/tcrangoprima-routing.module';

@NgModule({
  imports: [SharedModule, TCRANGOPRIMARoutingModule],
  declarations: [TCRANGOPRIMAComponent, TCRANGOPRIMADetailComponent, TCRANGOPRIMAUpdateComponent, TCRANGOPRIMADeleteDialogComponent],
  entryComponents: [TCRANGOPRIMADeleteDialogComponent],
})
export class TCRANGOPRIMAModule {}
