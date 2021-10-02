import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCUNIDADNEGOCIO } from '../tcunidadnegocio.model';
import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';
import { TCUNIDADNEGOCIODeleteDialogComponent } from '../delete/tcunidadnegocio-delete-dialog.component';

@Component({
  selector: 'jhi-tcunidadnegocio',
  templateUrl: './tcunidadnegocio.component.html',
})
export class TCUNIDADNEGOCIOComponent implements OnInit {
  tCUNIDADNEGOCIOS?: ITCUNIDADNEGOCIO[];
  isLoading = false;

  constructor(protected tCUNIDADNEGOCIOService: TCUNIDADNEGOCIOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCUNIDADNEGOCIOService.query().subscribe(
      (res: HttpResponse<ITCUNIDADNEGOCIO[]>) => {
        this.isLoading = false;
        this.tCUNIDADNEGOCIOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdUnidadNegocio(index: number, item: ITCUNIDADNEGOCIO): number {
    return item.idUnidadNegocio!;
  }

  delete(tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO): void {
    const modalRef = this.modalService.open(TCUNIDADNEGOCIODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCUNIDADNEGOCIO = tCUNIDADNEGOCIO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
