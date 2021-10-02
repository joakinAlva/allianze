import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCRANGOPRIMA } from '../tcrangoprima.model';

@Component({
  selector: 'jhi-tcrangoprima-detail',
  templateUrl: './tcrangoprima-detail.component.html',
})
export class TCRANGOPRIMADetailComponent implements OnInit {
  tCRANGOPRIMA: ITCRANGOPRIMA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCRANGOPRIMA }) => {
      this.tCRANGOPRIMA = tCRANGOPRIMA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
