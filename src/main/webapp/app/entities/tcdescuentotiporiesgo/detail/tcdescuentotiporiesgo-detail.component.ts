import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCDESCUENTOTIPORIESGO } from '../tcdescuentotiporiesgo.model';

@Component({
  selector: 'jhi-tcdescuentotiporiesgo-detail',
  templateUrl: './tcdescuentotiporiesgo-detail.component.html',
})
export class TCDESCUENTOTIPORIESGODetailComponent implements OnInit {
  tCDESCUENTOTIPORIESGO: ITCDESCUENTOTIPORIESGO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCDESCUENTOTIPORIESGO }) => {
      this.tCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
