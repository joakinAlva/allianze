import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTARIESGO } from '../tccuotariesgo.model';
import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

@Component({
  templateUrl: './tccuotariesgo-delete-dialog.component.html',
})
export class TCCUOTARIESGODeleteDialogComponent {
  tCCUOTARIESGO?: ITCCUOTARIESGO;

  constructor(protected tCCUOTARIESGOService: TCCUOTARIESGOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCUOTARIESGOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
