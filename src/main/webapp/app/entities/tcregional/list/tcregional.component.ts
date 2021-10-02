import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCREGIONAL } from '../tcregional.model';
import { TCREGIONALService } from '../service/tcregional.service';
import { TCREGIONALDeleteDialogComponent } from '../delete/tcregional-delete-dialog.component';

@Component({
  selector: 'jhi-tcregional',
  templateUrl: './tcregional.component.html',
})
export class TCREGIONALComponent implements OnInit {
  tCREGIONALS?: ITCREGIONAL[];
  isLoading = false;

  constructor(protected tCREGIONALService: TCREGIONALService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCREGIONALService.query().subscribe(
      (res: HttpResponse<ITCREGIONAL[]>) => {
        this.isLoading = false;
        this.tCREGIONALS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdRegional(index: number, item: ITCREGIONAL): number {
    return item.idRegional!;
  }

  delete(tCREGIONAL: ITCREGIONAL): void {
    const modalRef = this.modalService.open(TCREGIONALDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCREGIONAL = tCREGIONAL;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
