import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCEDADRECARGOComponent } from './list/tcedadrecargo.component';
import { TCEDADRECARGODetailComponent } from './detail/tcedadrecargo-detail.component';
import { TCEDADRECARGOUpdateComponent } from './update/tcedadrecargo-update.component';
import { TCEDADRECARGODeleteDialogComponent } from './delete/tcedadrecargo-delete-dialog.component';
import { TCEDADRECARGORoutingModule } from './route/tcedadrecargo-routing.module';

@NgModule({
  imports: [SharedModule, TCEDADRECARGORoutingModule],
  declarations: [TCEDADRECARGOComponent, TCEDADRECARGODetailComponent, TCEDADRECARGOUpdateComponent, TCEDADRECARGODeleteDialogComponent],
  entryComponents: [TCEDADRECARGODeleteDialogComponent],
})
export class TCEDADRECARGOModule {}
