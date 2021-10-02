import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCSUMAASEGURADAComponent } from './list/tcsumaasegurada.component';
import { TCSUMAASEGURADADetailComponent } from './detail/tcsumaasegurada-detail.component';
import { TCSUMAASEGURADAUpdateComponent } from './update/tcsumaasegurada-update.component';
import { TCSUMAASEGURADADeleteDialogComponent } from './delete/tcsumaasegurada-delete-dialog.component';
import { TCSUMAASEGURADARoutingModule } from './route/tcsumaasegurada-routing.module';

@NgModule({
  imports: [SharedModule, TCSUMAASEGURADARoutingModule],
  declarations: [
    TCSUMAASEGURADAComponent,
    TCSUMAASEGURADADetailComponent,
    TCSUMAASEGURADAUpdateComponent,
    TCSUMAASEGURADADeleteDialogComponent,
  ],
  entryComponents: [TCSUMAASEGURADADeleteDialogComponent],
})
export class TCSUMAASEGURADAModule {}
