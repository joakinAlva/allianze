import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCEJECUTIVO } from '../tcejecutivo.model';
import { TCEJECUTIVOService } from '../service/tcejecutivo.service';
import { TCEJECUTIVODeleteDialogComponent } from '../delete/tcejecutivo-delete-dialog.component';

@Component({
  selector: 'jhi-tcejecutivo',
  templateUrl: './tcejecutivo.component.html',
})
export class TCEJECUTIVOComponent implements OnInit {
  tCEJECUTIVOS?: ITCEJECUTIVO[];
  isLoading = false;

  constructor(protected tCEJECUTIVOService: TCEJECUTIVOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCEJECUTIVOService.query().subscribe(
      (res: HttpResponse<ITCEJECUTIVO[]>) => {
        this.isLoading = false;
        this.tCEJECUTIVOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdEjecutivo(index: number, item: ITCEJECUTIVO): number {
    return item.idEjecutivo!;
  }

  delete(tCEJECUTIVO: ITCEJECUTIVO): void {
    const modalRef = this.modalService.open(TCEJECUTIVODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCEJECUTIVO = tCEJECUTIVO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
