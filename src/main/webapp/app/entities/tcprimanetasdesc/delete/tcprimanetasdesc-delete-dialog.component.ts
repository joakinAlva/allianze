import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPRIMANETASDESC } from '../tcprimanetasdesc.model';
import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';

@Component({
  templateUrl: './tcprimanetasdesc-delete-dialog.component.html',
})
export class TCPRIMANETASDESCDeleteDialogComponent {
  tCPRIMANETASDESC?: ITCPRIMANETASDESC;

  constructor(protected tCPRIMANETASDESCService: TCPRIMANETASDESCService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCPRIMANETASDESCService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
