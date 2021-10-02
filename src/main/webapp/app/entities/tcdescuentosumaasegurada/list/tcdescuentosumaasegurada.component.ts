import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';
import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';
import { TCDESCUENTOSUMAASEGURADADeleteDialogComponent } from '../delete/tcdescuentosumaasegurada-delete-dialog.component';

@Component({
  selector: 'jhi-tcdescuentosumaasegurada',
  templateUrl: './tcdescuentosumaasegurada.component.html',
})
export class TCDESCUENTOSUMAASEGURADAComponent implements OnInit {
  tCDESCUENTOSUMAASEGURADAS?: ITCDESCUENTOSUMAASEGURADA[];
  isLoading = false;

  constructor(protected tCDESCUENTOSUMAASEGURADAService: TCDESCUENTOSUMAASEGURADAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCDESCUENTOSUMAASEGURADAService.query().subscribe(
      (res: HttpResponse<ITCDESCUENTOSUMAASEGURADA[]>) => {
        this.isLoading = false;
        this.tCDESCUENTOSUMAASEGURADAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdDescuentoSumaAsegurada(index: number, item: ITCDESCUENTOSUMAASEGURADA): number {
    return item.idDescuentoSumaAsegurada!;
  }

  delete(tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA): void {
    const modalRef = this.modalService.open(TCDESCUENTOSUMAASEGURADADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
