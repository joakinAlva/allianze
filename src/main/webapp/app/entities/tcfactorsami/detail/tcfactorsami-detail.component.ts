import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCFACTORSAMI } from '../tcfactorsami.model';

@Component({
  selector: 'jhi-tcfactorsami-detail',
  templateUrl: './tcfactorsami-detail.component.html',
})
export class TCFACTORSAMIDetailComponent implements OnInit {
  tCFACTORSAMI: ITCFACTORSAMI | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCFACTORSAMI }) => {
      this.tCFACTORSAMI = tCFACTORSAMI;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
