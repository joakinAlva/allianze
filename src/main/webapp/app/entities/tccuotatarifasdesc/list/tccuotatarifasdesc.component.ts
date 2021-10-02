import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';
import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';
import { TCCUOTATARIFASDESCDeleteDialogComponent } from '../delete/tccuotatarifasdesc-delete-dialog.component';

@Component({
  selector: 'jhi-tccuotatarifasdesc',
  templateUrl: './tccuotatarifasdesc.component.html',
})
export class TCCUOTATARIFASDESCComponent implements OnInit {
  tCCUOTATARIFASDESCS?: ITCCUOTATARIFASDESC[];
  isLoading = false;

  constructor(protected tCCUOTATARIFASDESCService: TCCUOTATARIFASDESCService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCUOTATARIFASDESCService.query().subscribe(
      (res: HttpResponse<ITCCUOTATARIFASDESC[]>) => {
        this.isLoading = false;
        this.tCCUOTATARIFASDESCS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCuotaTarifaSdesc(index: number, item: ITCCUOTATARIFASDESC): number {
    return item.idCuotaTarifaSdesc!;
  }

  delete(tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC): void {
    const modalRef = this.modalService.open(TCCUOTATARIFASDESCDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCUOTATARIFASDESC = tCCUOTATARIFASDESC;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
