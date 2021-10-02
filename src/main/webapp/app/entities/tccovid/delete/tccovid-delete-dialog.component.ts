import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOVID } from '../tccovid.model';
import { TCCOVIDService } from '../service/tccovid.service';

@Component({
  templateUrl: './tccovid-delete-dialog.component.html',
})
export class TCCOVIDDeleteDialogComponent {
  tCCOVID?: ITCCOVID;

  constructor(protected tCCOVIDService: TCCOVIDService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCCOVIDService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
