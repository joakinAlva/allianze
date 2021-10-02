import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCPERFIL } from '../tcperfil.model';

@Component({
  selector: 'jhi-tcperfil-detail',
  templateUrl: './tcperfil-detail.component.html',
})
export class TCPERFILDetailComponent implements OnInit {
  tCPERFIL: ITCPERFIL | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCPERFIL }) => {
      this.tCPERFIL = tCPERFIL;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
