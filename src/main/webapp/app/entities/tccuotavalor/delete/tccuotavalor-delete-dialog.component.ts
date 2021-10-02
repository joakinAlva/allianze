import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTAVALOR } from '../tccuotavalor.model';
import { TCCUOTAVALORService } from '../service/tccuotavalor.service';

@Component({
  templateUrl: './tccuotavalor-delete-dialog.component.html',
})
export class TCCUOTAVALORDeleteDialogComponent {
  tCCUOTAVALOR?: ITCCUOTAVALOR;

  constructor(protected tCCUOTAVALORService: TCCUOTAVALORService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCUOTAVALORService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
