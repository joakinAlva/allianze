import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCEDADRECARGO } from '../tcedadrecargo.model';
import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';
import { TCEDADRECARGODeleteDialogComponent } from '../delete/tcedadrecargo-delete-dialog.component';

@Component({
  selector: 'jhi-tcedadrecargo',
  templateUrl: './tcedadrecargo.component.html',
})
export class TCEDADRECARGOComponent implements OnInit {
  tCEDADRECARGOS?: ITCEDADRECARGO[];
  isLoading = false;

  constructor(protected tCEDADRECARGOService: TCEDADRECARGOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCEDADRECARGOService.query().subscribe(
      (res: HttpResponse<ITCEDADRECARGO[]>) => {
        this.isLoading = false;
        this.tCEDADRECARGOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdEdadRecargo(index: number, item: ITCEDADRECARGO): number {
    return item.idEdadRecargo!;
  }

  delete(tCEDADRECARGO: ITCEDADRECARGO): void {
    const modalRef = this.modalService.open(TCEDADRECARGODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCEDADRECARGO = tCEDADRECARGO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
