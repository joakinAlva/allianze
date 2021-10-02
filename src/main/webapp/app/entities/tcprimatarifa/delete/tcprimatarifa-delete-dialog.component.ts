import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPRIMATARIFA } from '../tcprimatarifa.model';
import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';

@Component({
  templateUrl: './tcprimatarifa-delete-dialog.component.html',
})
export class TCPRIMATARIFADeleteDialogComponent {
  tCPRIMATARIFA?: ITCPRIMATARIFA;

  constructor(protected tCPRIMATARIFAService: TCPRIMATARIFAService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tCPRIMATARIFAService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
