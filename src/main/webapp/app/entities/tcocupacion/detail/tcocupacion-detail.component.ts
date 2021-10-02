import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCOCUPACION } from '../tcocupacion.model';

@Component({
  selector: 'jhi-tcocupacion-detail',
  templateUrl: './tcocupacion-detail.component.html',
})
export class TCOCUPACIONDetailComponent implements OnInit {
  tCOCUPACION: ITCOCUPACION | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCOCUPACION }) => {
      this.tCOCUPACION = tCOCUPACION;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
