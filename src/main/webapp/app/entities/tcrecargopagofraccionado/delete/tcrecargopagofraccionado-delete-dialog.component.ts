import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';
import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';

@Component({
  templateUrl: './tcrecargopagofraccionado-delete-dialog.component.html',
})
export class TCRECARGOPAGOFRACCIONADODeleteDialogComponent {
  tCRECARGOPAGOFRACCIONADO?: ITCRECARGOPAGOFRACCIONADO;

  constructor(protected tCRECARGOPAGOFRACCIONADOService: TCRECARGOPAGOFRACCIONADOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCRECARGOPAGOFRACCIONADOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
