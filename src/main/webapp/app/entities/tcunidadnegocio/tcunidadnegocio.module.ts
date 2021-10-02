import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { TCUNIDADNEGOCIOComponent } from './list/tcunidadnegocio.component';
import { TCUNIDADNEGOCIODetailComponent } from './detail/tcunidadnegocio-detail.component';
import { TCUNIDADNEGOCIOUpdateComponent } from './update/tcunidadnegocio-update.component';
import { TCUNIDADNEGOCIODeleteDialogComponent } from './delete/tcunidadnegocio-delete-dialog.component';
import { TCUNIDADNEGOCIORoutingModule } from './route/tcunidadnegocio-routing.module';

@NgModule({
  imports: [SharedModule, TCUNIDADNEGOCIORoutingModule],
  declarations: [
    TCUNIDADNEGOCIOComponent,
    TCUNIDADNEGOCIODetailComponent,
    TCUNIDADNEGOCIOUpdateComponent,
    TCUNIDADNEGOCIODeleteDialogComponent,
  ],
  entryComponents: [TCUNIDADNEGOCIODeleteDialogComponent],
})
export class TCUNIDADNEGOCIOModule {}
