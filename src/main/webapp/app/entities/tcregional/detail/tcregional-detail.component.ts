import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCREGIONAL } from '../tcregional.model';

@Component({
  selector: 'jhi-tcregional-detail',
  templateUrl: './tcregional-detail.component.html',
})
export class TCREGIONALDetailComponent implements OnInit {
  tCREGIONAL: ITCREGIONAL | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCREGIONAL }) => {
      this.tCREGIONAL = tCREGIONAL;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
