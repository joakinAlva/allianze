import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPERFIL } from '../tcperfil.model';
import { TCPERFILService } from '../service/tcperfil.service';

@Component({
  templateUrl: './tcperfil-delete-dialog.component.html',
})
export class TCPERFILDeleteDialogComponent {
  tCPERFIL?: ITCPERFIL;

  constructor(protected tCPERFILService: TCPERFILService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCPERFILService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
