import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCFACTORDESCUENTO } from '../tcfactordescuento.model';
import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

@Component({
  templateUrl: './tcfactordescuento-delete-dialog.component.html',
})
export class TCFACTORDESCUENTODeleteDialogComponent {
  tCFACTORDESCUENTO?: ITCFACTORDESCUENTO;

  constructor(protected tCFACTORDESCUENTOService: TCFACTORDESCUENTOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCFACTORDESCUENTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
