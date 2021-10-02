import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCREGIONAL } from '../tcregional.model';
import { TCREGIONALService } from '../service/tcregional.service';

@Component({
  templateUrl: './tcregional-delete-dialog.component.html',
})
export class TCREGIONALDeleteDialogComponent {
  tCREGIONAL?: ITCREGIONAL;

  constructor(protected tCREGIONALService: TCREGIONALService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCREGIONALService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
