import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITMASEGURADO } from '../tmasegurado.model';

@Component({
  selector: 'jhi-tmasegurado-detail',
  templateUrl: './tmasegurado-detail.component.html',
})
export class TMASEGURADODetailComponent implements OnInit {
  tMASEGURADO: ITMASEGURADO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tMASEGURADO }) => {
      this.tMASEGURADO = tMASEGURADO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
