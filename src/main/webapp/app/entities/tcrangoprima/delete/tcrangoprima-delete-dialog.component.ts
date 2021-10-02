import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCRANGOPRIMA } from '../tcrangoprima.model';
import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

@Component({
  templateUrl: './tcrangoprima-delete-dialog.component.html',
})
export class TCRANGOPRIMADeleteDialogComponent {
  tCRANGOPRIMA?: ITCRANGOPRIMA;

  constructor(protected tCRANGOPRIMAService: TCRANGOPRIMAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCRANGOPRIMAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
