import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCFACTORDESCUENTO } from '../tcfactordescuento.model';
import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';
import { TCFACTORDESCUENTODeleteDialogComponent } from '../delete/tcfactordescuento-delete-dialog.component';

@Component({
  selector: 'jhi-tcfactordescuento',
  templateUrl: './tcfactordescuento.component.html',
})
export class TCFACTORDESCUENTOComponent implements OnInit {
  tCFACTORDESCUENTOS?: ITCFACTORDESCUENTO[];
  isLoading = false;

  constructor(protected tCFACTORDESCUENTOService: TCFACTORDESCUENTOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCFACTORDESCUENTOService.query().subscribe(
      (res: HttpResponse<ITCFACTORDESCUENTO[]>) => {
        this.isLoading = false;
        this.tCFACTORDESCUENTOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdFactorDescuento(index: number, item: ITCFACTORDESCUENTO): number {
    return item.idFactorDescuento!;
  }

  delete(tCFACTORDESCUENTO: ITCFACTORDESCUENTO): void {
    const modalRef = this.modalService.open(TCFACTORDESCUENTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCFACTORDESCUENTO = tCFACTORDESCUENTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
