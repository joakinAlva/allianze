import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPRIMANETASDESC } from '../tcprimanetasdesc.model';
import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';
import { TCPRIMANETASDESCDeleteDialogComponent } from '../delete/tcprimanetasdesc-delete-dialog.component';

@Component({
  selector: 'jhi-tcprimanetasdesc',
  templateUrl: './tcprimanetasdesc.component.html',
})
export class TCPRIMANETASDESCComponent implements OnInit {
  tCPRIMANETASDESCS?: ITCPRIMANETASDESC[];
  isLoading = false;

  constructor(protected tCPRIMANETASDESCService: TCPRIMANETASDESCService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCPRIMANETASDESCService.query().subscribe(
      (res: HttpResponse<ITCPRIMANETASDESC[]>) => {
        this.isLoading = false;
        this.tCPRIMANETASDESCS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdPrimaNetaSdesc(index: number, item: ITCPRIMANETASDESC): number {
    return item.idPrimaNetaSdesc!;
  }

  delete(tCPRIMANETASDESC: ITCPRIMANETASDESC): void {
    const modalRef = this.modalService.open(TCPRIMANETASDESCDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCPRIMANETASDESC = tCPRIMANETASDESC;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
