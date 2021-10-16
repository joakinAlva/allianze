import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMASEGURADO } from '../tmasegurado.model';
import { TMASEGURADOService } from '../service/tmasegurado.service';

@Component({
  templateUrl: './tmasegurado-delete-dialog.component.html',
})
export class TMASEGURADODeleteDialogComponent {
  tMASEGURADO?: ITMASEGURADO;

  constructor(protected tMASEGURADOService: TMASEGURADOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tMASEGURADOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
