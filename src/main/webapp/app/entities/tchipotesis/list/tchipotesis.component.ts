import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCHIPOTESIS } from '../tchipotesis.model';
import { TCHIPOTESISService } from '../service/tchipotesis.service';
import { TCHIPOTESISDeleteDialogComponent } from '../delete/tchipotesis-delete-dialog.component';

@Component({
  selector: 'jhi-tchipotesis',
  templateUrl: './tchipotesis.component.html',
})
export class TCHIPOTESISComponent implements OnInit {
  tCHIPOTESES?: ITCHIPOTESIS[];
  isLoading = false;

  constructor(protected tCHIPOTESISService: TCHIPOTESISService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCHIPOTESISService.query().subscribe(
      (res: HttpResponse<ITCHIPOTESIS[]>) => {
        this.isLoading = false;
        this.tCHIPOTESES = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdHipotesis(index: number, item: ITCHIPOTESIS): number {
    return item.idHipotesis!;
  }

  delete(tCHIPOTESIS: ITCHIPOTESIS): void {
    const modalRef = this.modalService.open(TCHIPOTESISDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCHIPOTESIS = tCHIPOTESIS;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
