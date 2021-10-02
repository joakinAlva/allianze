import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCSUMAASEGURADA } from '../tcsumaasegurada.model';
import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';

@Component({
  templateUrl: './tcsumaasegurada-delete-dialog.component.html',
})
export class TCSUMAASEGURADADeleteDialogComponent {
  tCSUMAASEGURADA?: ITCSUMAASEGURADA;

  constructor(protected tCSUMAASEGURADAService: TCSUMAASEGURADAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCSUMAASEGURADAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
