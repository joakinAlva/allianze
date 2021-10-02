import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCPRIMATARIFA } from '../tcprimatarifa.model';

@Component({
  selector: 'jhi-tcprimatarifa-detail',
  templateUrl: './tcprimatarifa-detail.component.html',
})
export class TCPRIMATARIFADetailComponent implements OnInit {
  tCPRIMATARIFA: ITCPRIMATARIFA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPRIMATARIFA }) => {
      this.tCPRIMATARIFA = tCPRIMATARIFA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
