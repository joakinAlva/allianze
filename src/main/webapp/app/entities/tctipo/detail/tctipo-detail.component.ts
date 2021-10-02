import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCTIPO } from '../tctipo.model';

@Component({
  selector: 'jhi-tctipo-detail',
  templateUrl: './tctipo-detail.component.html',
})
export class TCTIPODetailComponent implements OnInit {
  tCTIPO: ITCTIPO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPO }) => {
      this.tCTIPO = tCTIPO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
