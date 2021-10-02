import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCEDADRECARGO } from '../tcedadrecargo.model';

@Component({
  selector: 'jhi-tcedadrecargo-detail',
  templateUrl: './tcedadrecargo-detail.component.html',
})
export class TCEDADRECARGODetailComponent implements OnInit {
  tCEDADRECARGO: ITCEDADRECARGO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCEDADRECARGO }) => {
      this.tCEDADRECARGO = tCEDADRECARGO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
