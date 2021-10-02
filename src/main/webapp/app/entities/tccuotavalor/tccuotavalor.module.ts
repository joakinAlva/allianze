import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCUOTAVALORComponent } from './list/tccuotavalor.component';
import { TCCUOTAVALORDetailComponent } from './detail/tccuotavalor-detail.component';
import { TCCUOTAVALORUpdateComponent } from './update/tccuotavalor-update.component';
import { TCCUOTAVALORDeleteDialogComponent } from './delete/tccuotavalor-delete-dialog.component';
import { TCCUOTAVALORRoutingModule } from './route/tccuotavalor-routing.module';

@NgModule({
  imports: [SharedModule, TCCUOTAVALORRoutingModule],
  declarations: [TCCUOTAVALORComponent, TCCUOTAVALORDetailComponent, TCCUOTAVALORUpdateComponent, TCCUOTAVALORDeleteDialogComponent],
  entryComponents: [TCCUOTAVALORDeleteDialogComponent],
})
export class TCCUOTAVALORModule {}
