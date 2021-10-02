import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCCOBERTURAComponent } from './list/tccobertura.component';
import { TCCOBERTURADetailComponent } from './detail/tccobertura-detail.component';
import { TCCOBERTURAUpdateComponent } from './update/tccobertura-update.component';
import { TCCOBERTURADeleteDialogComponent } from './delete/tccobertura-delete-dialog.component';
import { TCCOBERTURARoutingModule } from './route/tccobertura-routing.module';

@NgModule({
  imports: [SharedModule, TCCOBERTURARoutingModule],
  declarations: [TCCOBERTURAComponent, TCCOBERTURADetailComponent, TCCOBERTURAUpdateComponent, TCCOBERTURADeleteDialogComponent],
  entryComponents: [TCCOBERTURADeleteDialogComponent],
})
export class TCCOBERTURAModule {}
