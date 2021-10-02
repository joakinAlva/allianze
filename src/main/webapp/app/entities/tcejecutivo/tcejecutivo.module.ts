import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCEJECUTIVOComponent } from './list/tcejecutivo.component';
import { TCEJECUTIVODetailComponent } from './detail/tcejecutivo-detail.component';
import { TCEJECUTIVOUpdateComponent } from './update/tcejecutivo-update.component';
import { TCEJECUTIVODeleteDialogComponent } from './delete/tcejecutivo-delete-dialog.component';
import { TCEJECUTIVORoutingModule } from './route/tcejecutivo-routing.module';

@NgModule({
  imports: [SharedModule, TCEJECUTIVORoutingModule],
  declarations: [TCEJECUTIVOComponent, TCEJECUTIVODetailComponent, TCEJECUTIVOUpdateComponent, TCEJECUTIVODeleteDialogComponent],
  entryComponents: [TCEJECUTIVODeleteDialogComponent],
})
export class TCEJECUTIVOModule {}
