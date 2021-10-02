import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCUOTARIESGO } from '../tccuotariesgo.model';

@Component({
  selector: 'jhi-tccuotariesgo-detail',
  templateUrl: './tccuotariesgo-detail.component.html',
})
export class TCCUOTARIESGODetailComponent implements OnInit {
  tCCUOTARIESGO: ITCCUOTARIESGO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTARIESGO }) => {
      this.tCCUOTARIESGO = tCCUOTARIESGO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
