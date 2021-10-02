import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCSUBGRUPO } from '../tcsubgrupo.model';
import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';

@Component({
  templateUrl: './tcsubgrupo-delete-dialog.component.html',
})
export class TCSUBGRUPODeleteDialogComponent {
  tCSUBGRUPO?: ITCSUBGRUPO;

  constructor(protected tCSUBGRUPOService: TCSUBGRUPOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCSUBGRUPOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
