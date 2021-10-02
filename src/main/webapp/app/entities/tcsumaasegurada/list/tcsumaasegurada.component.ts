import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCSUMAASEGURADA } from '../tcsumaasegurada.model';
import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';
import { TCSUMAASEGURADADeleteDialogComponent } from '../delete/tcsumaasegurada-delete-dialog.component';

@Component({
  selector: 'jhi-tcsumaasegurada',
  templateUrl: './tcsumaasegurada.component.html',
})
export class TCSUMAASEGURADAComponent implements OnInit {
  tCSUMAASEGURADAS?: ITCSUMAASEGURADA[];
  isLoading = false;

  constructor(protected tCSUMAASEGURADAService: TCSUMAASEGURADAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCSUMAASEGURADAService.query().subscribe(
      (res: HttpResponse<ITCSUMAASEGURADA[]>) => {
        this.isLoading = false;
        this.tCSUMAASEGURADAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdSumaAsegurada(index: number, item: ITCSUMAASEGURADA): number {
    return item.idSumaAsegurada!;
  }

  delete(tCSUMAASEGURADA: ITCSUMAASEGURADA): void {
    const modalRef = this.modalService.open(TCSUMAASEGURADADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCSUMAASEGURADA = tCSUMAASEGURADA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
