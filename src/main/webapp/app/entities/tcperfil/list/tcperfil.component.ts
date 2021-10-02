import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCPERFIL } from '../tcperfil.model';
import { TCPERFILService } from '../service/tcperfil.service';
import { TCPERFILDeleteDialogComponent } from '../delete/tcperfil-delete-dialog.component';

@Component({
  selector: 'jhi-tcperfil',
  templateUrl: './tcperfil.component.html',
})
export class TCPERFILComponent implements OnInit {
  tCPERFILS?: ITCPERFIL[];
  isLoading = false;

  constructor(protected tCPERFILService: TCPERFILService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCPERFILService.query().subscribe(
      (res: HttpResponse<ITCPERFIL[]>) => {
        this.isLoading = false;
        this.tCPERFILS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdPerfil(index: number, item: ITCPERFIL): number {
    return item.idPerfil!;
  }

  delete(tCPERFIL: ITCPERFIL): void {
    const modalRef = this.modalService.open(TCPERFILDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCPERFIL = tCPERFIL;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
