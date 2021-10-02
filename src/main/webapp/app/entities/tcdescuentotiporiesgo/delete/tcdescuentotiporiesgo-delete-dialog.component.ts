import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';
import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';

@Component({
  templateUrl: './tcdescuentotiporiesgo-delete-dialog.component.html',
})
export class TCDESCUENTOTIPORIESGODeleteDialogComponent {
  tCDESCUENTOTIPORIESGO?: ITCDESCUENTOTIPORIESGO;

  constructor(protected tCDESCUENTOTIPORIESGOService: TCDESCUENTOTIPORIESGOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCDESCUENTOTIPORIESGOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
