import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACIONEXPPROPIA } from '../tmcotizacionexppropia.model';
import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';
import { TMCOTIZACIONEXPPROPIADeleteDialogComponent } from '../delete/tmcotizacionexppropia-delete-dialog.component';

@Component({
  selector: 'jhi-tmcotizacionexppropia',
  templateUrl: './tmcotizacionexppropia.component.html',
})
export class TMCOTIZACIONEXPPROPIAComponent implements OnInit {
  tMCOTIZACIONEXPPROPIAS?: ITMCOTIZACIONEXPPROPIA[];
  isLoading = false;

  constructor(protected tMCOTIZACIONEXPPROPIAService: TMCOTIZACIONEXPPROPIAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tMCOTIZACIONEXPPROPIAService.query().subscribe(
      (res: HttpResponse<ITMCOTIZACIONEXPPROPIA[]>) => {
        this.isLoading = false;
        this.tMCOTIZACIONEXPPROPIAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCotizacionExpPropia(index: number, item: ITMCOTIZACIONEXPPROPIA): number {
    return item.idCotizacionExpPropia!;
  }

  delete(tMCOTIZACIONEXPPROPIA: ITMCOTIZACIONEXPPROPIA): void {
    const modalRef = this.modalService.open(TMCOTIZACIONEXPPROPIADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
