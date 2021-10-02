import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCONCEPTOComponent } from './list/tcconcepto.component';
import { TCCONCEPTODetailComponent } from './detail/tcconcepto-detail.component';
import { TCCONCEPTOUpdateComponent } from './update/tcconcepto-update.component';
import { TCCONCEPTODeleteDialogComponent } from './delete/tcconcepto-delete-dialog.component';
import { TCCONCEPTORoutingModule } from './route/tcconcepto-routing.module';

@NgModule({
  imports: [SharedModule, TCCONCEPTORoutingModule],
  declarations: [TCCONCEPTOComponent, TCCONCEPTODetailComponent, TCCONCEPTOUpdateComponent, TCCONCEPTODeleteDialogComponent],
  entryComponents: [TCCONCEPTODeleteDialogComponent],
})
export class TCCONCEPTOModule {}
