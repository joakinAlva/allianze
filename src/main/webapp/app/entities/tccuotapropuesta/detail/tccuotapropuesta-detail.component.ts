import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCCUOTAPROPUESTA } from '../tccuotapropuesta.model';

@Component({
  selector: 'jhi-tccuotapropuesta-detail',
  templateUrl: './tccuotapropuesta-detail.component.html',
})
export class TCCUOTAPROPUESTADetailComponent implements OnInit {
  tCCUOTAPROPUESTA: ITCCUOTAPROPUESTA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCCUOTAPROPUESTA }) => {
      this.tCCUOTAPROPUESTA = tCCUOTAPROPUESTA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
