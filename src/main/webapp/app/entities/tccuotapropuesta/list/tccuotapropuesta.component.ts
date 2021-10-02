import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITCCUOTAPROPUESTA } from '../tccuotapropuesta.model';
import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';
import { TCCUOTAPROPUESTADeleteDialogComponent } from '../delete/tccuotapropuesta-delete-dialog.component';

@Component({
  selector: 'jhi-tccuotapropuesta',
  templateUrl: './tccuotapropuesta.component.html',
})
export class TCCUOTAPROPUESTAComponent implements OnInit {
  tCCUOTAPROPUESTAS?: ITCCUOTAPROPUESTA[];
  isLoading = false;

  constructor(protected tCCUOTAPROPUESTAService: TCCUOTAPROPUESTAService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tCCUOTAPROPUESTAService.query().subscribe(
      (res: HttpResponse<ITCCUOTAPROPUESTA[]>) => {
        this.isLoading = false;
        this.tCCUOTAPROPUESTAS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackIdCuotaPropuesta(index: number, item: ITCCUOTAPROPUESTA): number {
    return item.idCuotaPropuesta!;
  }

  delete(tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA): void {
    const modalRef = this.modalService.open(TCCUOTAPROPUESTADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tCCUOTAPROPUESTA = tCCUOTAPROPUESTA;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
