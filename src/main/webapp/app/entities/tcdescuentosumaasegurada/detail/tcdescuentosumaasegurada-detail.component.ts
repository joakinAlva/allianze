import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';

@Component({
  selector: 'jhi-tcdescuentosumaasegurada-detail',
  templateUrl: './tcdescuentosumaasegurada-detail.component.html',
})
export class TCDESCUENTOSUMAASEGURADADetailComponent implements OnInit {
  tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tCDESCUENTOSUMAASEGURADA }) => {
      this.tCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
