import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCREFENCIA } from '../tcrefencia.model';
import { TCREFENCIAService } from '../service/tcrefencia.service';
import { TCREFENCIADeleteDialogComponent } from '../delete/tcrefencia-delete-dialog.component';

@Component({
  selector: 'jhi-tcrefencia',
  templateUrl: './tcrefencia.component.html',
})
export class TCREFENCIAComponent implements OnInit {
  tCREFENCIAS?: ITCREFENCIA[];
  isLoading = false;

  constructor(protected tCREFENCIAService: TCREFENCIAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCREFENCIAService.query().subscribe(
      (res: HttpResponse<ITCREFENCIA[]>) => {
        this.isLoading = false;
        this.tCREFENCIAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdReferencia(index: number, item: ITCREFENCIA): number {
    return item.idReferencia!;
  }

  delete(tCREFENCIA: ITCREFENCIA): void {
    const modalRef = this.modalService.open(TCREFENCIADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCREFENCIA = tCREFENCIA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
