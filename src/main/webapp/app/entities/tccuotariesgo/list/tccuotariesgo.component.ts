import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTARIESGO } from '../tccuotariesgo.model';
import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';
import { TCCUOTARIESGODeleteDialogComponent } from '../delete/tccuotariesgo-delete-dialog.component';

@Component({
  selector: 'jhi-tccuotariesgo',
  templateUrl: './tccuotariesgo.component.html',
})
export class TCCUOTARIESGOComponent implements OnInit {
  tCCUOTARIESGOS?: ITCCUOTARIESGO[];
  isLoading = false;

  constructor(protected tCCUOTARIESGOService: TCCUOTARIESGOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCUOTARIESGOService.query().subscribe(
      (res: HttpResponse<ITCCUOTARIESGO[]>) => {
        this.isLoading = false;
        this.tCCUOTARIESGOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCuotaRiesgo(index: number, item: ITCCUOTARIESGO): number {
    return item.idCuotaRiesgo!;
  }

  delete(tCCUOTARIESGO: ITCCUOTARIESGO): void {
    const modalRef = this.modalService.open(TCCUOTARIESGODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCUOTARIESGO = tCCUOTARIESGO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
