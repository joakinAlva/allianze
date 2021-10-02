import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITMUSUARIO } from '../tmusuario.model';

@Component({
  selector: 'jhi-tmusuario-detail',
  templateUrl: './tmusuario-detail.component.html',
})
export class TMUSUARIODetailComponent implements OnInit {
  tMUSUARIO: ITMUSUARIO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMUSUARIO }) => {
      this.tMUSUARIO = tMUSUARIO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
