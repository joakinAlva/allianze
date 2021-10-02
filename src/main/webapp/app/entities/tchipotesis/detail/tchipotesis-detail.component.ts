import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCHIPOTESIS } from '../tchipotesis.model';

@Component({
  selector: 'jhi-tchipotesis-detail',
  templateUrl: './tchipotesis-detail.component.html',
})
export class TCHIPOTESISDetailComponent implements OnInit {
  tCHIPOTESIS: ITCHIPOTESIS | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCHIPOTESIS }) => {
      this.tCHIPOTESIS = tCHIPOTESIS;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
