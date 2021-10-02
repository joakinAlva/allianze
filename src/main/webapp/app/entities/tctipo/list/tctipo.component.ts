import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPO } from '../tctipo.model';
import { TCTIPOService } from '../service/tctipo.service';
import { TCTIPODeleteDialogComponent } from '../delete/tctipo-delete-dialog.component';

@Component({
  selector: 'jhi-tctipo',
  templateUrl: './tctipo.component.html',
})
export class TCTIPOComponent implements OnInit {
  tCTIPOS?: ITCTIPO[];
  isLoading = false;

  constructor(protected tCTIPOService: TCTIPOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCTIPOService.query().subscribe(
      (res: HttpResponse<ITCTIPO[]>) => {
        this.isLoading = false;
        this.tCTIPOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdTipo(index: number, item: ITCTIPO): number {
    return item.idTipo!;
  }

  delete(tCTIPO: ITCTIPO): void {
    const modalRef = this.modalService.open(TCTIPODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCTIPO = tCTIPO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
