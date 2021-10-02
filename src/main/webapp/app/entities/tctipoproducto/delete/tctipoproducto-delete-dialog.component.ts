import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPOPRODUCTO } from '../tctipoproducto.model';
import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';

@Component({
  templateUrl: './tctipoproducto-delete-dialog.component.html',
})
export class TCTIPOPRODUCTODeleteDialogComponent {
  tCTIPOPRODUCTO?: ITCTIPOPRODUCTO;

  constructor(protected tCTIPOPRODUCTOService: TCTIPOPRODUCTOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCTIPOPRODUCTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
