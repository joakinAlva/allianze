import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCOEFICIENTEComponent } from './list/tccoeficiente.component';
import { TCCOEFICIENTEDetailComponent } from './detail/tccoeficiente-detail.component';
import { TCCOEFICIENTEUpdateComponent } from './update/tccoeficiente-update.component';
import { TCCOEFICIENTEDeleteDialogComponent } from './delete/tccoeficiente-delete-dialog.component';
import { TCCOEFICIENTERoutingModule } from './route/tccoeficiente-routing.module';

@NgModule({
  imports: [SharedModule, TCCOEFICIENTERoutingModule],
  declarations: [TCCOEFICIENTEComponent, TCCOEFICIENTEDetailComponent, TCCOEFICIENTEUpdateComponent, TCCOEFICIENTEDeleteDialogComponent],
  entryComponents: [TCCOEFICIENTEDeleteDialogComponent],
})
export class TCCOEFICIENTEModule {}
