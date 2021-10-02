import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOBERTURA } from '../tccobertura.model';
import { TCCOBERTURAService } from '../service/tccobertura.service';

@Component({
  templateUrl: './tccobertura-delete-dialog.component.html',
})
export class TCCOBERTURADeleteDialogComponent {
  tCCOBERTURA?: ITCCOBERTURA;

  constructor(protected tCCOBERTURAService: TCCOBERTURAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCOBERTURAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
