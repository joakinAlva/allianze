import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOEFICIENTE } from '../tccoeficiente.model';
import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';
import { TCCOEFICIENTEDeleteDialogComponent } from '../delete/tccoeficiente-delete-dialog.component';

@Component({
  selector: 'jhi-tccoeficiente',
  templateUrl: './tccoeficiente.component.html',
})
export class TCCOEFICIENTEComponent implements OnInit {
  tCCOEFICIENTES?: ITCCOEFICIENTE[];
  isLoading = false;

  constructor(protected tCCOEFICIENTEService: TCCOEFICIENTEService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCOEFICIENTEService.query().subscribe(
      (res: HttpResponse<ITCCOEFICIENTE[]>) => {
        this.isLoading = false;
        this.tCCOEFICIENTES = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCoeficiente(index: number, item: ITCCOEFICIENTE): number {
    return item.idCoeficiente!;
  }

  delete(tCCOEFICIENTE: ITCCOEFICIENTE): void {
    const modalRef = this.modalService.open(TCCOEFICIENTEDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCOEFICIENTE = tCCOEFICIENTE;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
