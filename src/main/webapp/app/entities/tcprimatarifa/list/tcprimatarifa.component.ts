import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPRIMATARIFA } from '../tcprimatarifa.model';
import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';
import { TCPRIMATARIFADeleteDialogComponent } from '../delete/tcprimatarifa-delete-dialog.component';

@Component({
  selector: 'jhi-tcprimatarifa',
  templateUrl: './tcprimatarifa.component.html',
})
export class TCPRIMATARIFAComponent implements OnInit {
  tCPRIMATARIFAS?: ITCPRIMATARIFA[];
  isLoading = false;

  constructor(protected tCPRIMATARIFAService: TCPRIMATARIFAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCPRIMATARIFAService.query().subscribe(
      (res: HttpResponse<ITCPRIMATARIFA[]>) => {
        this.isLoading = false;
        this.tCPRIMATARIFAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdPrimaTarifa(index: number, item: ITCPRIMATARIFA): number {
    return item.idPrimaTarifa!;
  }

  delete(tCPRIMATARIFA: ITCPRIMATARIFA): void {
    const modalRef = this.modalService.open(TCPRIMATARIFADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCPRIMATARIFA = tCPRIMATARIFA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
