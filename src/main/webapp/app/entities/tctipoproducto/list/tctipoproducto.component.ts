import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCTIPOPRODUCTO } from '../tctipoproducto.model';
import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';
import { TCTIPOPRODUCTODeleteDialogComponent } from '../delete/tctipoproducto-delete-dialog.component';

@Component({
  selector: 'jhi-tctipoproducto',
  templateUrl: './tctipoproducto.component.html',
})
export class TCTIPOPRODUCTOComponent implements OnInit {
  tCTIPOPRODUCTOS?: ITCTIPOPRODUCTO[];
  isLoading = false;

  constructor(protected tCTIPOPRODUCTOService: TCTIPOPRODUCTOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCTIPOPRODUCTOService.query().subscribe(
      (res: HttpResponse<ITCTIPOPRODUCTO[]>) => {
        this.isLoading = false;
        this.tCTIPOPRODUCTOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdTipoProducto(index: number, item: ITCTIPOPRODUCTO): number {
    return item.idTipoProducto!;
  }

  delete(tCTIPOPRODUCTO: ITCTIPOPRODUCTO): void {
    const modalRef = this.modalService.open(TCTIPOPRODUCTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCTIPOPRODUCTO = tCTIPOPRODUCTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
