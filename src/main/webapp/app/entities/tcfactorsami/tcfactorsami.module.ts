import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCFACTORSAMIComponent } from './list/tcfactorsami.component';
import { TCFACTORSAMIDetailComponent } from './detail/tcfactorsami-detail.component';
import { TCFACTORSAMIUpdateComponent } from './update/tcfactorsami-update.component';
import { TCFACTORSAMIDeleteDialogComponent } from './delete/tcfactorsami-delete-dialog.component';
import { TCFACTORSAMIRoutingModule } from './route/tcfactorsami-routing.module';

@NgModule({
  imports: [SharedModule, TCFACTORSAMIRoutingModule],
  declarations: [TCFACTORSAMIComponent, TCFACTORSAMIDetailComponent, TCFACTORSAMIUpdateComponent, TCFACTORSAMIDeleteDialogComponent],
  entryComponents: [TCFACTORSAMIDeleteDialogComponent],
})
export class TCFACTORSAMIModule {}
