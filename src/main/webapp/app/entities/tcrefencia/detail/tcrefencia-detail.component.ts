import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCREFENCIA } from '../tcrefencia.model';

@Component({
  selector: 'jhi-tcrefencia-detail',
  templateUrl: './tcrefencia-detail.component.html',
})
export class TCREFENCIADetailComponent implements OnInit {
  tCREFENCIA: ITCREFENCIA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCREFENCIA }) => {
      this.tCREFENCIA = tCREFENCIA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
