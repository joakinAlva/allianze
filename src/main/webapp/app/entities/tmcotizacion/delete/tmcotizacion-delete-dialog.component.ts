import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACION } from '../tmcotizacion.model';
import { TMCOTIZACIONService } from '../service/tmcotizacion.service';

@Component({
  templateUrl: './tmcotizacion-delete-dialog.component.html',
})
export class TMCOTIZACIONDeleteDialogComponent {
  tMCOTIZACION?: ITMCOTIZACION;

  constructor(protected tMCOTIZACIONService: TMCOTIZACIONService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tMCOTIZACIONService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
