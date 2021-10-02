import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCSUBGRUPOComponent } from './list/tcsubgrupo.component';
import { TCSUBGRUPODetailComponent } from './detail/tcsubgrupo-detail.component';
import { TCSUBGRUPOUpdateComponent } from './update/tcsubgrupo-update.component';
import { TCSUBGRUPODeleteDialogComponent } from './delete/tcsubgrupo-delete-dialog.component';
import { TCSUBGRUPORoutingModule } from './route/tcsubgrupo-routing.module';

@NgModule({
  imports: [SharedModule, TCSUBGRUPORoutingModule],
  declarations: [TCSUBGRUPOComponent, TCSUBGRUPODetailComponent, TCSUBGRUPOUpdateComponent, TCSUBGRUPODeleteDialogComponent],
  entryComponents: [TCSUBGRUPODeleteDialogComponent],
})
export class TCSUBGRUPOModule {}
