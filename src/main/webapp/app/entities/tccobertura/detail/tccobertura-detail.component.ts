import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCOBERTURA } from '../tccobertura.model';

@Component({
  selector: 'jhi-tccobertura-detail',
  templateUrl: './tccobertura-detail.component.html',
})
export class TCCOBERTURADetailComponent implements OnInit {
  tCCOBERTURA: ITCCOBERTURA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCOBERTURA }) => {
      this.tCCOBERTURA = tCCOBERTURA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
