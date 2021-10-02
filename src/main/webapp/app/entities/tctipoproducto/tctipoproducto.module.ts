import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCTIPOPRODUCTOComponent } from './list/tctipoproducto.component';
import { TCTIPOPRODUCTODetailComponent } from './detail/tctipoproducto-detail.component';
import { TCTIPOPRODUCTOUpdateComponent } from './update/tctipoproducto-update.component';
import { TCTIPOPRODUCTODeleteDialogComponent } from './delete/tctipoproducto-delete-dialog.component';
import { TCTIPOPRODUCTORoutingModule } from './route/tctipoproducto-routing.module';

@NgModule({
  imports: [SharedModule, TCTIPOPRODUCTORoutingModule],
  declarations: [
    TCTIPOPRODUCTOComponent,
    TCTIPOPRODUCTODetailComponent,
    TCTIPOPRODUCTOUpdateComponent,
    TCTIPOPRODUCTODeleteDialogComponent,
  ],
  entryComponents: [TCTIPOPRODUCTODeleteDialogComponent],
})
export class TCTIPOPRODUCTOModule {}
