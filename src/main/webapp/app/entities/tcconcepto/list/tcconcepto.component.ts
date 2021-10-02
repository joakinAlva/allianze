import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCONCEPTO } from '../tcconcepto.model';
import { TCCONCEPTOService } from '../service/tcconcepto.service';
import { TCCONCEPTODeleteDialogComponent } from '../delete/tcconcepto-delete-dialog.component';

@Component({
  selector: 'jhi-tcconcepto',
  templateUrl: './tcconcepto.component.html',
})
export class TCCONCEPTOComponent implements OnInit {
  tCCONCEPTOS?: ITCCONCEPTO[];
  isLoading = false;

  constructor(protected tCCONCEPTOService: TCCONCEPTOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCONCEPTOService.query().subscribe(
      (res: HttpResponse<ITCCONCEPTO[]>) => {
        this.isLoading = false;
        this.tCCONCEPTOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdConcepto(index: number, item: ITCCONCEPTO): number {
    return item.idConcepto!;
  }

  delete(tCCONCEPTO: ITCCONCEPTO): void {
    const modalRef = this.modalService.open(TCCONCEPTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCONCEPTO = tCCONCEPTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
