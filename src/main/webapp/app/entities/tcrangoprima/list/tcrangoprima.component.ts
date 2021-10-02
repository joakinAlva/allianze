import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCRANGOPRIMA } from '../tcrangoprima.model';
import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';
import { TCRANGOPRIMADeleteDialogComponent } from '../delete/tcrangoprima-delete-dialog.component';

@Component({
  selector: 'jhi-tcrangoprima',
  templateUrl: './tcrangoprima.component.html',
})
export class TCRANGOPRIMAComponent implements OnInit {
  tCRANGOPRIMAS?: ITCRANGOPRIMA[];
  isLoading = false;

  constructor(protected tCRANGOPRIMAService: TCRANGOPRIMAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCRANGOPRIMAService.query().subscribe(
      (res: HttpResponse<ITCRANGOPRIMA[]>) => {
        this.isLoading = false;
        this.tCRANGOPRIMAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdRangoPrima(index: number, item: ITCRANGOPRIMA): number {
    return item.idRangoPrima!;
  }

  delete(tCRANGOPRIMA: ITCRANGOPRIMA): void {
    const modalRef = this.modalService.open(TCRANGOPRIMADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCRANGOPRIMA = tCRANGOPRIMA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
