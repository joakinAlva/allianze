import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCFACTORSAMI } from '../tcfactorsami.model';
import { TCFACTORSAMIService } from '../service/tcfactorsami.service';
import { TCFACTORSAMIDeleteDialogComponent } from '../delete/tcfactorsami-delete-dialog.component';

@Component({
  selector: 'jhi-tcfactorsami',
  templateUrl: './tcfactorsami.component.html',
})
export class TCFACTORSAMIComponent implements OnInit {
  tCFACTORSAMIS?: ITCFACTORSAMI[];
  isLoading = false;

  constructor(protected tCFACTORSAMIService: TCFACTORSAMIService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCFACTORSAMIService.query().subscribe(
      (res: HttpResponse<ITCFACTORSAMI[]>) => {
        this.isLoading = false;
        this.tCFACTORSAMIS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdFactorSami(index: number, item: ITCFACTORSAMI): number {
    return item.idFactorSami!;
  }

  delete(tCFACTORSAMI: ITCFACTORSAMI): void {
    const modalRef = this.modalService.open(TCFACTORSAMIDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCFACTORSAMI = tCFACTORSAMI;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
