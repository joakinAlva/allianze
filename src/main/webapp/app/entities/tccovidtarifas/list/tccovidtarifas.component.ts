import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOVIDTARIFAS } from '../tccovidtarifas.model';
import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';
import { TCCOVIDTARIFASDeleteDialogComponent } from '../delete/tccovidtarifas-delete-dialog.component';

@Component({
  selector: 'jhi-tccovidtarifas',
  templateUrl: './tccovidtarifas.component.html',
})
export class TCCOVIDTARIFASComponent implements OnInit {
  tCCOVIDTARIFAS?: ITCCOVIDTARIFAS[];
  isLoading = false;

  constructor(protected tCCOVIDTARIFASService: TCCOVIDTARIFASService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCOVIDTARIFASService.query().subscribe(
      (res: HttpResponse<ITCCOVIDTARIFAS[]>) => {
        this.isLoading = false;
        this.tCCOVIDTARIFAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCovidTarifas(index: number, item: ITCCOVIDTARIFAS): number {
    return item.idCovidTarifas!;
  }

  delete(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): void {
    const modalRef = this.modalService.open(TCCOVIDTARIFASDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCOVIDTARIFAS = tCCOVIDTARIFAS;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
