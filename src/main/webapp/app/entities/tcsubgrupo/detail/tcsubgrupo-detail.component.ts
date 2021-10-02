import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCSUBGRUPO } from '../tcsubgrupo.model';

@Component({
  selector: 'jhi-tcsubgrupo-detail',
  templateUrl: './tcsubgrupo-detail.component.html',
})
export class TCSUBGRUPODetailComponent implements OnInit {
  tCSUBGRUPO: ITCSUBGRUPO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCSUBGRUPO }) => {
      this.tCSUBGRUPO = tCSUBGRUPO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
