import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMASEGURADO } from '../tmasegurado.model';
import { TMASEGURADOService } from '../service/tmasegurado.service';
import { TMASEGURADODeleteDialogComponent } from '../delete/tmasegurado-delete-dialog.component';

@Component({
  selector: 'jhi-tmasegurado',
  templateUrl: './tmasegurado.component.html',
})
export class TMASEGURADOComponent implements OnInit {
  tMASEGURADOS?: ITMASEGURADO[];
  isLoading = false;

  constructor(protected tMASEGURADOService: TMASEGURADOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tMASEGURADOService.query().subscribe(
      (res: HttpResponse<ITMASEGURADO[]>) => {
        this.isLoading = false;
        this.tMASEGURADOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdAsegurados(index: number, item: ITMASEGURADO): number {
    return item.idAsegurados!;
  }

  delete(tMASEGURADO: ITMASEGURADO): void {
    const modalRef = this.modalService.open(TMASEGURADODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tMASEGURADO = tMASEGURADO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
