import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCESTATUSCOTIZACION } from '../tcestatuscotizacion.model';

@Component({
  selector: 'jhi-tcestatuscotizacion-detail',
  templateUrl: './tcestatuscotizacion-detail.component.html',
})
export class TCESTATUSCOTIZACIONDetailComponent implements OnInit {
  tCESTATUSCOTIZACION: ITCESTATUSCOTIZACION | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCESTATUSCOTIZACION }) => {
      this.tCESTATUSCOTIZACION = tCESTATUSCOTIZACION;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
