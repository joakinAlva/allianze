import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITMCOTIZACION } from '../tmcotizacion.model';

@Component({
  selector: 'jhi-tmcotizacion-detail',
  templateUrl: './tmcotizacion-detail.component.html',
})
export class TMCOTIZACIONDetailComponent implements OnInit {
  tMCOTIZACION: ITMCOTIZACION | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACION }) => {
      this.tMCOTIZACION = tMCOTIZACION;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
