import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCONCEPTO } from '../tcconcepto.model';

@Component({
  selector: 'jhi-tcconcepto-detail',
  templateUrl: './tcconcepto-detail.component.html',
})
export class TCCONCEPTODetailComponent implements OnInit {
  tCCONCEPTO: ITCCONCEPTO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCONCEPTO }) => {
      this.tCCONCEPTO = tCCONCEPTO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
