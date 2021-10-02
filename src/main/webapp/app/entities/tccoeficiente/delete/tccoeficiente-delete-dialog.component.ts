import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOEFICIENTE } from '../tccoeficiente.model';
import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';

@Component({
  templateUrl: './tccoeficiente-delete-dialog.component.html',
})
export class TCCOEFICIENTEDeleteDialogComponent {
  tCCOEFICIENTE?: ITCCOEFICIENTE;

  constructor(protected tCCOEFICIENTEService: TCCOEFICIENTEService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCOEFICIENTEService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
