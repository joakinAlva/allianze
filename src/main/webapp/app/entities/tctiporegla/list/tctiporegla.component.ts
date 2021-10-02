import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPOREGLA } from '../tctiporegla.model';
import { TCTIPOREGLAService } from '../service/tctiporegla.service';
import { TCTIPOREGLADeleteDialogComponent } from '../delete/tctiporegla-delete-dialog.component';

@Component({
  selector: 'jhi-tctiporegla',
  templateUrl: './tctiporegla.component.html',
})
export class TCTIPOREGLAComponent implements OnInit {
  tCTIPOREGLAS?: ITCTIPOREGLA[];
  isLoading = false;

  constructor(protected tCTIPOREGLAService: TCTIPOREGLAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCTIPOREGLAService.query().subscribe(
      (res: HttpResponse<ITCTIPOREGLA[]>) => {
        this.isLoading = false;
        this.tCTIPOREGLAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdTipoRegla(index: number, item: ITCTIPOREGLA): number {
    return item.idTipoRegla!;
  }

  delete(tCTIPOREGLA: ITCTIPOREGLA): void {
    const modalRef = this.modalService.open(TCTIPOREGLADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCTIPOREGLA = tCTIPOREGLA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
