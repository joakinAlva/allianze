import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPOREGLA } from '../tctiporegla.model';
import { TCTIPOREGLAService } from '../service/tctiporegla.service';

@Component({
  templateUrl: './tctiporegla-delete-dialog.component.html',
})
export class TCTIPOREGLADeleteDialogComponent {
  tCTIPOREGLA?: ITCTIPOREGLA;

  constructor(protected tCTIPOREGLAService: TCTIPOREGLAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCTIPOREGLAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
