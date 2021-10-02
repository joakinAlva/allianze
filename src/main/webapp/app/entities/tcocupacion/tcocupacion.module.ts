import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCOCUPACIONComponent } from './list/tcocupacion.component';
import { TCOCUPACIONDetailComponent } from './detail/tcocupacion-detail.component';
import { TCOCUPACIONUpdateComponent } from './update/tcocupacion-update.component';
import { TCOCUPACIONDeleteDialogComponent } from './delete/tcocupacion-delete-dialog.component';
import { TCOCUPACIONRoutingModule } from './route/tcocupacion-routing.module';

@NgModule({
  imports: [SharedModule, TCOCUPACIONRoutingModule],
  declarations: [TCOCUPACIONComponent, TCOCUPACIONDetailComponent, TCOCUPACIONUpdateComponent, TCOCUPACIONDeleteDialogComponent],
  entryComponents: [TCOCUPACIONDeleteDialogComponent],
})
export class TCOCUPACIONModule {}
