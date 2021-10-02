import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';

@Component({
  selector: 'jhi-tccuotatarifasdesc-detail',
  templateUrl: './tccuotatarifasdesc-detail.component.html',
})
export class TCCUOTATARIFASDESCDetailComponent implements OnInit {
  tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTATARIFASDESC }) => {
      this.tCCUOTATARIFASDESC = tCCUOTATARIFASDESC;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
