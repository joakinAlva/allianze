import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCFACTORSAMI } from '../tcfactorsami.model';
import { TCFACTORSAMIService } from '../service/tcfactorsami.service';

@Component({
  templateUrl: './tcfactorsami-delete-dialog.component.html',
})
export class TCFACTORSAMIDeleteDialogComponent {
  tCFACTORSAMI?: ITCFACTORSAMI;

  constructor(protected tCFACTORSAMIService: TCFACTORSAMIService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCFACTORSAMIService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
