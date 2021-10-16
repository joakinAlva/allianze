import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';
import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';

@Component({
  templateUrl: './tcestatuscotizacion-delete-dialog.component.html',
})
export class TCESTATUSCOTIZACIONDeleteDialogComponent {
  tCESTATUSCOTIZACION?: ITCESTATUSCOTIZACION;

  constructor(protected tCESTATUSCOTIZACIONService: TCESTATUSCOTIZACIONService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCESTATUSCOTIZACIONService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
