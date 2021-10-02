import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCREGIONALComponent } from './list/tcregional.component';
import { TCREGIONALDetailComponent } from './detail/tcregional-detail.component';
import { TCREGIONALUpdateComponent } from './update/tcregional-update.component';
import { TCREGIONALDeleteDialogComponent } from './delete/tcregional-delete-dialog.component';
import { TCREGIONALRoutingModule } from './route/tcregional-routing.module';

@NgModule({
  imports: [SharedModule, TCREGIONALRoutingModule],
  declarations: [TCREGIONALComponent, TCREGIONALDetailComponent, TCREGIONALUpdateComponent, TCREGIONALDeleteDialogComponent],
  entryComponents: [TCREGIONALDeleteDialogComponent],
})
export class TCREGIONALModule {}
