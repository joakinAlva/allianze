import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTAPROPUESTA } from '../tccuotapropuesta.model';
import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';

@Component({
  templateUrl: './tccuotapropuesta-delete-dialog.component.html',
})
export class TCCUOTAPROPUESTADeleteDialogComponent {
  tCCUOTAPROPUESTA?: ITCCUOTAPROPUESTA;

  constructor(protected tCCUOTAPROPUESTAService: TCCUOTAPROPUESTAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCUOTAPROPUESTAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
