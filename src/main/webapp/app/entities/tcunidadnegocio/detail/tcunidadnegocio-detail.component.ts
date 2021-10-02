import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCUNIDADNEGOCIO } from '../tcunidadnegocio.model';

@Component({
  selector: 'jhi-tcunidadnegocio-detail',
  templateUrl: './tcunidadnegocio-detail.component.html',
})
export class TCUNIDADNEGOCIODetailComponent implements OnInit {
  tCUNIDADNEGOCIO: ITCUNIDADNEGOCIO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCUNIDADNEGOCIO }) => {
      this.tCUNIDADNEGOCIO = tCUNIDADNEGOCIO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
