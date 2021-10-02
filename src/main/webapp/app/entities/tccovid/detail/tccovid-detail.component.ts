import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCOVID } from '../tccovid.model';

@Component({
  selector: 'jhi-tccovid-detail',
  templateUrl: './tccovid-detail.component.html',
})
export class TCCOVIDDetailComponent implements OnInit {
  tCCOVID: ITCCOVID | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOVID }) => {
      this.tCCOVID = tCCOVID;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
