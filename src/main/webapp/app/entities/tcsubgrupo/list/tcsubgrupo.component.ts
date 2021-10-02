import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCSUBGRUPO } from '../tcsubgrupo.model';
import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';
import { TCSUBGRUPODeleteDialogComponent } from '../delete/tcsubgrupo-delete-dialog.component';

@Component({
  selector: 'jhi-tcsubgrupo',
  templateUrl: './tcsubgrupo.component.html',
})
export class TCSUBGRUPOComponent implements OnInit {
  tCSUBGRUPOS?: ITCSUBGRUPO[];
  isLoading = false;

  constructor(protected tCSUBGRUPOService: TCSUBGRUPOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCSUBGRUPOService.query().subscribe(
      (res: HttpResponse<ITCSUBGRUPO[]>) => {
        this.isLoading = false;
        this.tCSUBGRUPOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdSubGrupo(index: number, item: ITCSUBGRUPO): number {
    return item.idSubGrupo!;
  }

  delete(tCSUBGRUPO: ITCSUBGRUPO): void {
    const modalRef = this.modalService.open(TCSUBGRUPODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCSUBGRUPO = tCSUBGRUPO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
