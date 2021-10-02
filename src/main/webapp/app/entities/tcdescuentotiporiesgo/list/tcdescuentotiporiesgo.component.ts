import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';
import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';
import { TCDESCUENTOTIPORIESGODeleteDialogComponent } from '../delete/tcdescuentotiporiesgo-delete-dialog.component';

@Component({
  selector: 'jhi-tcdescuentotiporiesgo',
  templateUrl: './tcdescuentotiporiesgo.component.html',
})
export class TCDESCUENTOTIPORIESGOComponent implements OnInit {
  tCDESCUENTOTIPORIESGOS?: ITCDESCUENTOTIPORIESGO[];
  isLoading = false;

  constructor(protected tCDESCUENTOTIPORIESGOService: TCDESCUENTOTIPORIESGOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCDESCUENTOTIPORIESGOService.query().subscribe(
      (res: HttpResponse<ITCDESCUENTOTIPORIESGO[]>) => {
        this.isLoading = false;
        this.tCDESCUENTOTIPORIESGOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdDescuentoTipoRiesgo(index: number, item: ITCDESCUENTOTIPORIESGO): number {
    return item.idDescuentoTipoRiesgo!;
  }

  delete(tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO): void {
    const modalRef = this.modalService.open(TCDESCUENTOTIPORIESGODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
