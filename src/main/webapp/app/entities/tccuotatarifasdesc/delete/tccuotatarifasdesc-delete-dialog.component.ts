import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';
import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';

@Component({
  templateUrl: './tccuotatarifasdesc-delete-dialog.component.html',
})
export class TCCUOTATARIFASDESCDeleteDialogComponent {
  tCCUOTATARIFASDESC?: ITCCUOTATARIFASDESC;

  constructor(protected tCCUOTATARIFASDESCService: TCCUOTATARIFASDESCService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCUOTATARIFASDESCService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
