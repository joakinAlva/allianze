import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCEDADRECARGO } from '../tcedadrecargo.model';
import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';

@Component({
  templateUrl: './tcedadrecargo-delete-dialog.component.html',
})
export class TCEDADRECARGODeleteDialogComponent {
  tCEDADRECARGO?: ITCEDADRECARGO;

  constructor(protected tCEDADRECARGOService: TCEDADRECARGOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCEDADRECARGOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
