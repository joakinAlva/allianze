import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TMASEGURADOComponent } from './list/tmasegurado.component';
import { TMASEGURADODetailComponent } from './detail/tmasegurado-detail.component';
import { TMASEGURADOUpdateComponent } from './update/tmasegurado-update.component';
import { TMASEGURADODeleteDialogComponent } from './delete/tmasegurado-delete-dialog.component';
import { TMASEGURADORoutingModule } from './route/tmasegurado-routing.module';

@NgModule({
  imports: [SharedModule, TMASEGURADORoutingModule],
  declarations: [TMASEGURADOComponent, TMASEGURADODetailComponent, TMASEGURADOUpdateComponent, TMASEGURADODeleteDialogComponent],
  entryComponents: [TMASEGURADODeleteDialogComponent],
})
export class TMASEGURADOModule {}
