import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCEJECUTIVO } from '../tcejecutivo.model';
import { TCEJECUTIVOService } from '../service/tcejecutivo.service';

@Component({
  templateUrl: './tcejecutivo-delete-dialog.component.html',
})
export class TCEJECUTIVODeleteDialogComponent {
  tCEJECUTIVO?: ITCEJECUTIVO;

  constructor(protected tCEJECUTIVOService: TCEJECUTIVOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCEJECUTIVOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
