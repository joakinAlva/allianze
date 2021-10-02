import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCEJECUTIVO } from '../tcejecutivo.model';

@Component({
  selector: 'jhi-tcejecutivo-detail',
  templateUrl: './tcejecutivo-detail.component.html',
})
export class TCEJECUTIVODetailComponent implements OnInit {
  tCEJECUTIVO: ITCEJECUTIVO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCEJECUTIVO }) => {
      this.tCEJECUTIVO = tCEJECUTIVO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
