import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCUNIDADNEGOCIO } from '../tcunidadnegocio.model';
import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

@Component({
  templateUrl: './tcunidadnegocio-delete-dialog.component.html',
})
export class TCUNIDADNEGOCIODeleteDialogComponent {
  tCUNIDADNEGOCIO?: ITCUNIDADNEGOCIO;

  constructor(protected tCUNIDADNEGOCIOService: TCUNIDADNEGOCIOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCUNIDADNEGOCIOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
