import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCOEFICIENTE } from '../tccoeficiente.model';

@Component({
  selector: 'jhi-tccoeficiente-detail',
  templateUrl: './tccoeficiente-detail.component.html',
})
export class TCCOEFICIENTEDetailComponent implements OnInit {
  tCCOEFICIENTE: ITCCOEFICIENTE | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOEFICIENTE }) => {
      this.tCCOEFICIENTE = tCCOEFICIENTE;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
