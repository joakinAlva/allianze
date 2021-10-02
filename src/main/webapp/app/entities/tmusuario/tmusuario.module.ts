import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TMUSUARIOComponent } from './list/tmusuario.component';
import { TMUSUARIODetailComponent } from './detail/tmusuario-detail.component';
import { TMUSUARIOUpdateComponent } from './update/tmusuario-update.component';
import { TMUSUARIODeleteDialogComponent } from './delete/tmusuario-delete-dialog.component';
import { TMUSUARIORoutingModule } from './route/tmusuario-routing.module';

@NgModule({
  imports: [SharedModule, TMUSUARIORoutingModule],
  declarations: [TMUSUARIOComponent, TMUSUARIODetailComponent, TMUSUARIOUpdateComponent, TMUSUARIODeleteDialogComponent],
  entryComponents: [TMUSUARIODeleteDialogComponent],
})
export class TMUSUARIOModule {}
