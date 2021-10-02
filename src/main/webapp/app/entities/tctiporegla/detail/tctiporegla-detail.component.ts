import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCTIPOREGLA } from '../tctiporegla.model';

@Component({
  selector: 'jhi-tctiporegla-detail',
  templateUrl: './tctiporegla-detail.component.html',
})
export class TCTIPOREGLADetailComponent implements OnInit {
  tCTIPOREGLA: ITCTIPOREGLA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPOREGLA }) => {
      this.tCTIPOREGLA = tCTIPOREGLA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
