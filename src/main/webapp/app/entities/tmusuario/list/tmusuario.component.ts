import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITMUSUARIO } from '../tmusuario.model';
import { TMUSUARIOService } from '../service/tmusuario.service';
import { TMUSUARIODeleteDialogComponent } from '../delete/tmusuario-delete-dialog.component';

@Component({
  selector: 'jhi-tmusuario',
  templateUrl: './tmusuario.component.html',
})
export class TMUSUARIOComponent implements OnInit {
  tMUSUARIOS?: ITMUSUARIO[];
  isLoading = false;

  constructor(protected tMUSUARIOService: TMUSUARIOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tMUSUARIOService.query().subscribe(
      (res: HttpResponse<ITMUSUARIO[]>) => {
        this.isLoading = false;
        this.tMUSUARIOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdUsuario(index: number, item: ITMUSUARIO): number {
    return item.idUsuario!;
  }

  delete(tMUSUARIO: ITMUSUARIO): void {
    const modalRef = this.modalService.open(TMUSUARIODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tMUSUARIO = tMUSUARIO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
