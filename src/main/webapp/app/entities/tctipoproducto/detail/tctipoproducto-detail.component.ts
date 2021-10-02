import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCTIPOPRODUCTO } from '../tctipoproducto.model';

@Component({
  selector: 'jhi-tctipoproducto-detail',
  templateUrl: './tctipoproducto-detail.component.html',
})
export class TCTIPOPRODUCTODetailComponent implements OnInit {
  tCTIPOPRODUCTO: ITCTIPOPRODUCTO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCTIPOPRODUCTO }) => {
      this.tCTIPOPRODUCTO = tCTIPOPRODUCTO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
