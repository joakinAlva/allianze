import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCRECARGOPAGOFRACCIONADO } from '../tcrecargopagofraccionado.model';
import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';
import { TCRECARGOPAGOFRACCIONADODeleteDialogComponent } from '../delete/tcrecargopagofraccionado-delete-dialog.component';

@Component({
  selector: 'jhi-tcrecargopagofraccionado',
  templateUrl: './tcrecargopagofraccionado.component.html',
})
export class TCRECARGOPAGOFRACCIONADOComponent implements OnInit {
  tCRECARGOPAGOFRACCIONADOS?: ITCRECARGOPAGOFRACCIONADO[];
  isLoading = false;

  constructor(protected tCRECARGOPAGOFRACCIONADOService: TCRECARGOPAGOFRACCIONADOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCRECARGOPAGOFRACCIONADOService.query().subscribe(
      (res: HttpResponse<ITCRECARGOPAGOFRACCIONADO[]>) => {
        this.isLoading = false;
        this.tCRECARGOPAGOFRACCIONADOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdRecargoPagoFraccionado(index: number, item: ITCRECARGOPAGOFRACCIONADO): number {
    return item.idRecargoPagoFraccionado!;
  }

  delete(tCRECARGOPAGOFRACCIONADO: ITCRECARGOPAGOFRACCIONADO): void {
    const modalRef = this.modalService.open(TCRECARGOPAGOFRACCIONADODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
