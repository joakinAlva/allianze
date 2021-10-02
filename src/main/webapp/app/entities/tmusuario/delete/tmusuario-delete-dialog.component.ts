import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMUSUARIO } from '../tmusuario.model';
import { TMUSUARIOService } from '../service/tmusuario.service';

@Component({
  templateUrl: './tmusuario-delete-dialog.component.html',
})
export class TMUSUARIODeleteDialogComponent {
  tMUSUARIO?: ITMUSUARIO;

  constructor(protected tMUSUARIOService: TMUSUARIOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tMUSUARIOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
