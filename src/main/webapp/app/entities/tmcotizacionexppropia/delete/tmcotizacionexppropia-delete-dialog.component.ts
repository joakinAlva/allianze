import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';
import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';

@Component({
  templateUrl: './tmcotizacionexppropia-delete-dialog.component.html',
})
export class TMCOTIZACIONEXPPROPIADeleteDialogComponent {
  tMCOTIZACIONEXPPROPIA?: ITMCOTIZACIONEXPPROPIA;

  constructor(protected tMCOTIZACIONEXPPROPIAService: TMCOTIZACIONEXPPROPIAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tMCOTIZACIONEXPPROPIAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
