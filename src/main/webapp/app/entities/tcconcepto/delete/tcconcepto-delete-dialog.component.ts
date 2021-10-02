import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCONCEPTO } from '../tcconcepto.model';
import { TCCONCEPTOService } from '../service/tcconcepto.service';

@Component({
  templateUrl: './tcconcepto-delete-dialog.component.html',
})
export class TCCONCEPTODeleteDialogComponent {
  tCCONCEPTO?: ITCCONCEPTO;

  constructor(protected tCCONCEPTOService: TCCONCEPTOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCONCEPTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
