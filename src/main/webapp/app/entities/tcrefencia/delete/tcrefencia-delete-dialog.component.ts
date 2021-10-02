import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCREFENCIA } from '../tcrefencia.model';
import { TCREFENCIAService } from '../service/tcrefencia.service';

@Component({
  templateUrl: './tcrefencia-delete-dialog.component.html',
})
export class TCREFENCIADeleteDialogComponent {
  tCREFENCIA?: ITCREFENCIA;

  constructor(protected tCREFENCIAService: TCREFENCIAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCREFENCIAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
