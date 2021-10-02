import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCHIPOTESISComponent } from './list/tchipotesis.component';
import { TCHIPOTESISDetailComponent } from './detail/tchipotesis-detail.component';
import { TCHIPOTESISUpdateComponent } from './update/tchipotesis-update.component';
import { TCHIPOTESISDeleteDialogComponent } from './delete/tchipotesis-delete-dialog.component';
import { TCHIPOTESISRoutingModule } from './route/tchipotesis-routing.module';

@NgModule({
  imports: [SharedModule, TCHIPOTESISRoutingModule],
  declarations: [TCHIPOTESISComponent, TCHIPOTESISDetailComponent, TCHIPOTESISUpdateComponent, TCHIPOTESISDeleteDialogComponent],
  entryComponents: [TCHIPOTESISDeleteDialogComponent],
})
export class TCHIPOTESISModule {}
