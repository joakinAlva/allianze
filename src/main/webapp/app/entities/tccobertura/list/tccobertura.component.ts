import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCOBERTURA } from '../tccobertura.model';
import { TCCOBERTURAService } from '../service/tccobertura.service';
import { TCCOBERTURADeleteDialogComponent } from '../delete/tccobertura-delete-dialog.component';

@Component({
  selector: 'jhi-tccobertura',
  templateUrl: './tccobertura.component.html',
})
export class TCCOBERTURAComponent implements OnInit {
  tCCOBERTURAS?: ITCCOBERTURA[];
  isLoading = false;

  constructor(protected tCCOBERTURAService: TCCOBERTURAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCOBERTURAService.query().subscribe(
      (res: HttpResponse<ITCCOBERTURA[]>) => {
        this.isLoading = false;
        this.tCCOBERTURAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCobertura(index: number, item: ITCCOBERTURA): number {
    return item.idCobertura!;
  }

  delete(tCCOBERTURA: ITCCOBERTURA): void {
    const modalRef = this.modalService.open(TCCOBERTURADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCOBERTURA = tCCOBERTURA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
