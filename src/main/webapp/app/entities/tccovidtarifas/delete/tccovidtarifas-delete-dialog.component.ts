import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOVIDTARIFAS } from '../tccovidtarifas.model';
import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';

@Component({
  templateUrl: './tccovidtarifas-delete-dialog.component.html',
})
export class TCCOVIDTARIFASDeleteDialogComponent {
  tCCOVIDTARIFAS?: ITCCOVIDTARIFAS;

  constructor(protected tCCOVIDTARIFASService: TCCOVIDTARIFASService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCOVIDTARIFASService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
