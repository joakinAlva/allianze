import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';
import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';
import { TCESTATUSCOTIZACIONDeleteDialogComponent } from '../delete/tcestatuscotizacion-delete-dialog.component';

@Component({
  selector: 'jhi-tcestatuscotizacion',
  templateUrl: './tcestatuscotizacion.component.html',
})
export class TCESTATUSCOTIZACIONComponent implements OnInit {
  tCESTATUSCOTIZACIONS?: ITCESTATUSCOTIZACION[];
  isLoading = false;

  constructor(protected tCESTATUSCOTIZACIONService: TCESTATUSCOTIZACIONService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCESTATUSCOTIZACIONService.query().subscribe(
      (res: HttpResponse<ITCESTATUSCOTIZACION[]>) => {
        this.isLoading = false;
        this.tCESTATUSCOTIZACIONS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdEstatusCotizacion(index: number, item: ITCESTATUSCOTIZACION): number {
    return item.idEstatusCotizacion!;
  }

  delete(tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION): void {
    const modalRef = this.modalService.open(TCESTATUSCOTIZACIONDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCESTATUSCOTIZACION = tCESTATUSCOTIZACION;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
