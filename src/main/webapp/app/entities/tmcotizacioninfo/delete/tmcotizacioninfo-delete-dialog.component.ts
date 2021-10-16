import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACIONINFO } from '../tmcotizacioninfo.model';
import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';

@Component({
  templateUrl: './tmcotizacioninfo-delete-dialog.component.html',
})
export class TMCOTIZACIONINFODeleteDialogComponent {
  tMCOTIZACIONINFO?: ITMCOTIZACIONINFO;

  constructor(protected tMCOTIZACIONINFOService: TMCOTIZACIONINFOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tMCOTIZACIONINFOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
