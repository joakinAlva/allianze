import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPO } from '../tctipo.model';
import { TCTIPOService } from '../service/tctipo.service';

@Component({
  templateUrl: './tctipo-delete-dialog.component.html',
})
export class TCTIPODeleteDialogComponent {
  tCTIPO?: ITCTIPO;

  constructor(protected tCTIPOService: TCTIPOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCTIPOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
