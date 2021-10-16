import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMCOTIZACIONINFO } from '../tmcotizacioninfo.model';
import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';
import { TMCOTIZACIONINFODeleteDialogComponent } from '../delete/tmcotizacioninfo-delete-dialog.component';

@Component({
  selector: 'jhi-tmcotizacioninfo',
  templateUrl: './tmcotizacioninfo.component.html',
})
export class TMCOTIZACIONINFOComponent implements OnInit {
  tMCOTIZACIONINFOS?: ITMCOTIZACIONINFO[];
  isLoading = false;

  constructor(protected tMCOTIZACIONINFOService: TMCOTIZACIONINFOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tMCOTIZACIONINFOService.query().subscribe(
      (res: HttpResponse<ITMCOTIZACIONINFO[]>) => {
        this.isLoading = false;
        this.tMCOTIZACIONINFOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCotizacionInfo(index: number, item: ITMCOTIZACIONINFO): number {
    return item.idCotizacionInfo!;
  }

  delete(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): void {
    const modalRef = this.modalService.open(TMCOTIZACIONINFODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tMCOTIZACIONINFO = tMCOTIZACIONINFO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
