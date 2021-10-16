import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCOVIDTARIFAS } from '../tccovidtarifas.model';

@Component({
  selector: 'jhi-tccovidtarifas-detail',
  templateUrl: './tccovidtarifas-detail.component.html',
})
export class TCCOVIDTARIFASDetailComponent implements OnInit {
  tCCOVIDTARIFAS: ITCCOVIDTARIFAS | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOVIDTARIFAS }) => {
      this.tCCOVIDTARIFAS = tCCOVIDTARIFAS;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
