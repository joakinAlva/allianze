import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACION } from '../tmcotizacion.model';
import { TMCOTIZACIONService } from '../service/tmcotizacion.service';
import { TMCOTIZACIONDeleteDialogComponent } from '../delete/tmcotizacion-delete-dialog.component';

@Component({
  selector: 'jhi-tmcotizacion',
  templateUrl: './tmcotizacion.component.html',
})
export class TMCOTIZACIONComponent implements OnInit {
  tMCOTIZACIONS?: ITMCOTIZACION[];
  isLoading = false;

  constructor(protected tMCOTIZACIONService: TMCOTIZACIONService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tMCOTIZACIONService.query().subscribe(
      (res: HttpResponse<ITMCOTIZACION[]>) => {
        this.isLoading = false;
        this.tMCOTIZACIONS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCotizacion(index: number, item: ITMCOTIZACION): number {
    return item.idCotizacion!;
  }

  delete(tMCOTIZACION: ITMCOTIZACION): void {
    const modalRef = this.modalService.open(TMCOTIZACIONDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tMCOTIZACION = tMCOTIZACION;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
