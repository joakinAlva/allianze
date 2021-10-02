import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCHIPOTESIS } from '../tchipotesis.model';
import { TCHIPOTESISService } from '../service/tchipotesis.service';

@Component({
  templateUrl: './tchipotesis-delete-dialog.component.html',
})
export class TCHIPOTESISDeleteDialogComponent {
  tCHIPOTESIS?: ITCHIPOTESIS;

  constructor(protected tCHIPOTESISService: TCHIPOTESISService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCHIPOTESISService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
