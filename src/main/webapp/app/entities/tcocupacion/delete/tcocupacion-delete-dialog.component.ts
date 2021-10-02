import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCOCUPACION } from '../tcocupacion.model';
import { TCOCUPACIONService } from '../service/tcocupacion.service';

@Component({
  templateUrl: './tcocupacion-delete-dialog.component.html',
})
export class TCOCUPACIONDeleteDialogComponent {
  tCOCUPACION?: ITCOCUPACION;

  constructor(protected tCOCUPACIONService: TCOCUPACIONService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCOCUPACIONService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
