import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITMCOTIZACIONINFO } from '../tmcotizacioninfo.model';

@Component({
  selector: 'jhi-tmcotizacioninfo-detail',
  templateUrl: './tmcotizacioninfo-detail.component.html',
})
export class TMCOTIZACIONINFODetailComponent implements OnInit {
  tMCOTIZACIONINFO: ITMCOTIZACIONINFO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMCOTIZACIONINFO }) => {
      this.tMCOTIZACIONINFO = tMCOTIZACIONINFO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
