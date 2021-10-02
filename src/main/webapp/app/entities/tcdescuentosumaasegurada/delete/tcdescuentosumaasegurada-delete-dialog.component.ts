import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';
import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';

@Component({
  templateUrl: './tcdescuentosumaasegurada-delete-dialog.component.html',
})
export class TCDESCUENTOSUMAASEGURADADeleteDialogComponent {
  tCDESCUENTOSUMAASEGURADA?: ITCDESCUENTOSUMAASEGURADA;

  constructor(protected tCDESCUENTOSUMAASEGURADAService: TCDESCUENTOSUMAASEGURADAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCDESCUENTOSUMAASEGURADAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
