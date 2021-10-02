import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCPRIMANETASDESC } from '../tcprimanetasdesc.model';

@Component({
  selector: 'jhi-tcprimanetasdesc-detail',
  templateUrl: './tcprimanetasdesc-detail.component.html',
})
export class TCPRIMANETASDESCDetailComponent implements OnInit {
  tCPRIMANETASDESC: ITCPRIMANETASDESC | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPRIMANETASDESC }) => {
      this.tCPRIMANETASDESC = tCPRIMANETASDESC;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
