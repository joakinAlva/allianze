import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCOCUPACION } from '../tcocupacion.model';
import { TCOCUPACIONService } from '../service/tcocupacion.service';
import { TCOCUPACIONDeleteDialogComponent } from '../delete/tcocupacion-delete-dialog.component';

@Component({
  selector: 'jhi-tcocupacion',
  templateUrl: './tcocupacion.component.html',
})
export class TCOCUPACIONComponent implements OnInit {
  tCOCUPACIONS?: ITCOCUPACION[];
  isLoading = false;

  constructor(protected tCOCUPACIONService: TCOCUPACIONService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCOCUPACIONService.query().subscribe(
      (res: HttpResponse<ITCOCUPACION[]>) => {
        this.isLoading = false;
        this.tCOCUPACIONS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdOcupacion(index: number, item: ITCOCUPACION): number {
    return item.idOcupacion!;
  }

  delete(tCOCUPACION: ITCOCUPACION): void {
    const modalRef = this.modalService.open(TCOCUPACIONDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCOCUPACION = tCOCUPACION;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
