import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTAVALOR } from '../tccuotavalor.model';
import { TCCUOTAVALORService } from '../service/tccuotavalor.service';
import { TCCUOTAVALORDeleteDialogComponent } from '../delete/tccuotavalor-delete-dialog.component';

@Component({
  selector: 'jhi-tccuotavalor',
  templateUrl: './tccuotavalor.component.html',
})
export class TCCUOTAVALORComponent implements OnInit {
  tCCUOTAVALORS?: ITCCUOTAVALOR[];
  isLoading = false;

  constructor(protected tCCUOTAVALORService: TCCUOTAVALORService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCUOTAVALORService.query().subscribe(
      (res: HttpResponse<ITCCUOTAVALOR[]>) => {
        this.isLoading = false;
        this.tCCUOTAVALORS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCuotaValor(index: number, item: ITCCUOTAVALOR): number {
    return item.idCuotaValor!;
  }

  delete(tCCUOTAVALOR: ITCCUOTAVALOR): void {
    const modalRef = this.modalService.open(TCCUOTAVALORDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCUOTAVALOR = tCCUOTAVALOR;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
