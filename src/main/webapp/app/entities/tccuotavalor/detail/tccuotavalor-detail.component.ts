import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCUOTAVALOR } from '../tccuotavalor.model';

@Component({
  selector: 'jhi-tccuotavalor-detail',
  templateUrl: './tccuotavalor-detail.component.html',
})
export class TCCUOTAVALORDetailComponent implements OnInit {
  tCCUOTAVALOR: ITCCUOTAVALOR | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTAVALOR }) => {
      this.tCCUOTAVALOR = tCCUOTAVALOR;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
