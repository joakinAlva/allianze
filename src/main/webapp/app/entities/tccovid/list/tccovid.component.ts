import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOVID } from '../tccovid.model';
import { TCCOVIDService } from '../service/tccovid.service';
import { TCCOVIDDeleteDialogComponent } from '../delete/tccovid-delete-dialog.component';

@Component({
  selector: 'jhi-tccovid',
  templateUrl: './tccovid.component.html',
})
export class TCCOVIDComponent implements OnInit {
  tCCOVIDS?: ITCCOVID[];
  isLoading = false;

  constructor(protected tCCOVIDService: TCCOVIDService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCOVIDService.query().subscribe(
      (res: HttpResponse<ITCCOVID[]>) => {
        this.isLoading = false;
        this.tCCOVIDS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCovid(index: number, item: ITCCOVID): number {
    return item.idCovid!;
  }

  delete(tCCOVID: ITCCOVID): void {
    const modalRef = this.modalService.open(TCCOVIDDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCOVID = tCCOVID;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
